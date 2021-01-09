package user.qq.ppy;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

public class FriendNodeRender extends JLabel implements TreeCellRenderer {

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean
        expanded,boolean leaf, int row, boolean hasFocus) {
		
		FriendNode friendNode = (FriendNode) value;
		
		String text="";
        
		if(friendNode.getIsOnline() == -1)
		{
			setEnabled(false);
			text="<html>" + friendNode.getName() + "(¿Îœﬂ)" + "<br/>" + friendNode.getSig() + "<br/>" + " <html/>";
		} 
		else if(friendNode.getIsOnline() == 1){
			setEnabled(true);
			text="<html>" + friendNode.getName() + "(‘⁄œﬂ)" + "<br/>" + friendNode.getSig() + "<br/>" + " <html/>";
		}
		else{
			text="<html>" + friendNode.getName() + "<br/>" + friendNode.getSig() + "<br/>" + " <html/>";
		}

		setText(text);

		ImageIcon ii = new ImageIcon(friendNode.getIcon());
	    ii.setImage(ii.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
	    setIcon(ii);
	    setIconTextGap(15);	
	    return this;
	}

}