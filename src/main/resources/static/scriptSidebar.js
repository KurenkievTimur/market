$('.laptop-btn').hover(function () {
    $('nav.sidebar ul .laptop-show').toggleClass("show");
    $('nav.sidebar ul .first').toggleClass("rotate");
})

$('.phone-btn').hover(function () {
    $('nav.sidebar ul .phone-show').toggleClass("show1");
    $('nav.sidebar ul .second').toggleClass("rotate");
})
