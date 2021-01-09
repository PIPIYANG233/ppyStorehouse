$(function () {
    $(".main_center").slideDown("slow");
})

    function show_() {
    $("#show_btn").hide("slow", "swing");
    $(".left").show("slow", "swing");
}

    function hide_() {
    $(".left").hide("slow", "swing");
    $("#show_btn").show();
}