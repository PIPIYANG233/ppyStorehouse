function show_main_right() {
    $(".main_right").slideDown("slow");
    $("#head_img_change").prop("src", $("#head_img").attr("src"));
}

function hide_main_right() {
    $(".main_right").slideUp("slow");
}
