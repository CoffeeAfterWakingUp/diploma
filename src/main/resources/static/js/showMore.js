$(".show-more").click(function () {
    if($(".course_desc_body").hasClass("show-more-height")) {
        $(this).text("(Show Less)");
    } else {
        $(this).text("(Show More)");
    }

    $(".course_desc_body").toggleClass("show-more-height");
});