package service.tools;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import domain.User;
import service.userOption.UserOptionImp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class Upload {
    // 上传文件存储目录
    private static final String SAVE_HEAD_PATH = "F:\\ppy.blogs\\web\\user_head";
    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    /**
     * 上传数据及保存文件
     */
    public static void updateHead(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setHeaderEncoding("UTF-8");

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        File head_img = new File(item.getName());
                        String fileName = id + getSuffix(head_img);
                        String filePath = SAVE_HEAD_PATH + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        request.getSession().setAttribute("head_path", "/user_head/" + fileName);
                        new UserOptionImp().updateHead(id, "/user_head/" + fileName);
                        User user = (User)(request.getSession().getAttribute("user"));
                        user.setHead_img("/user_head/" + fileName);
                        request.getSession().setAttribute("user", user);
                        request.getSession().setAttribute("message", "更换成功");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "错误信息: " + e.getMessage());
        }
    }

    public static String getSuffix(File file){
        if(file == null){
            return null;
        }
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") == -1){
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
