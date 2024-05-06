(function ($) {
    "use strict";

    // Dropdown on mouse hover
    $(document).ready(function () {
        function toggleNavbarMethod() {
            if ($(window).width() > 992) {
                $('.navbar .dropdown').on('mouseover', function () {
                    $('.dropdown-toggle', this).trigger('click');
                }).on('mouseout', function () {
                    $('.dropdown-toggle', this).trigger('click').blur();
                });
            } else {
                $('.navbar .dropdown').off('mouseover').off('mouseout');
            }
        }

        toggleNavbarMethod();
        $(window).resize(toggleNavbarMethod);
    });


    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Vendor carousel
    $('.vendor-carousel').owlCarousel({
        loop: true, margin: 29, nav: false, autoplay: true, smartSpeed: 1000, responsive: {
            0: {
                items: 2
            }, 576: {
                items: 3
            }, 768: {
                items: 4
            }, 992: {
                items: 5
            }, 1200: {
                items: 6
            }
        }
    });


    // Related carousel
    $('.related-carousel').owlCarousel({
        loop: true, margin: 29, nav: false, autoplay: true, smartSpeed: 1000, responsive: {
            0: {
                items: 1
            }, 576: {
                items: 2
            }, 768: {
                items: 3
            }, 992: {
                items: 4
            }
        }
    });

    //show category list on index page
    if (window.location.pathname == "/") $("#navbar-vertical").addClass("show"); else $("#navbar-vertical").removeClass("show");

    //category position absolute in another page except index
    if (window.location.pathname == "/" || window.location.pathname == "/trang-chu") {
        $("#navbar-vertical").removeClass(["position-absolute", "bg-light", "style-width-cat"]);
    } else {
        $("#navbar-vertical").addClass(["position-absolute", "bg-light", "style-width-cat"]);
    }

    //load ajax cho trang san pham
    $.ajax({
        url: "/danh-sach-san-pham" + window.location.search,
        cache: false,
        dataType: "json",
        success: function (result) {
            show(result)
        }
    });

    $("input[type=radio][name=filterByPrice]").click(function () {
        filterByPrice();
    })

    $(".content-link").click(function () {
        $(".content-link").removeClass("active");
        $(this).addClass("active");
    })

    //ajax user login
    $.ajax({
        url: "getUser", cache: false, dataType: "json", success: function (user) {
            var str = "";
            str += "<a href='/gio-hang' class='nav-item nav-link' title='Giỏ hàng'><i class = 'fas fa-shopping-cart text-primary' ></i><span class='badge'>0</span></a>";
            if (user.email != null) {
                str += "<a href='/thong-tin-tai-khoan' class='nav-item nav-link' title='Thông tin tài khoản'> Xin chào, " + user.username + "</a>";
                for (let i of user.roles) {
                    if (i.name == "ROLE_ADMIN") {
                        str += "<a href='/admin-page/book-management' class='nav-item nav-link'>Trang Admin</a>";
                    }
                }
                str += "<a href='/logout' class='nav-item nav-link' title='Đăng xuất'>Thoát</a>";
            } else {
                str += "<a href='/dang-nhap' class='nav-item nav-link'>Đăng nhập</a>";
            }
            $("#user-area").html(str);
        }
    });

})(jQuery);

function show(response) {
    //result là danh sách bookdto của bookoutput, bên bookcontroller sẽ trả về
    $("#list-book").html(showBook(response.result));
    $("#pagination").html(showPagination(response.page, response.totalPage));
}

//tham số page sẽ được lấy từ url
function movePage(page) {
    // ?category=truyen-tranh&order=asc
    var urlParameter = window.location.search;
    $.ajax({
        //lấy từ vị trí 1 là bỏ dấu ?, vd như urlParameter
        // là ?category=truyen-tranh thì subString(1) là lấy từ category
        url: "/danh-sach-san-pham?page=" + page + "&" + urlParameter.substring(1),
        cache: false,
        dataType: "json",
        success: function (result) {
            show(result)
        }
    });
}

function showBook(listBook) {
    var str = "";
    for (let book of listBook) {
        str += "<div class='col-lg-4 col-md-6 col-sm-12 pb-1'>";
        str += "<div class='card product-item border-0 mb-4'>";
        str += "<div style='height: 250px' class='card-header product-img position-relative overflow-hidden bg-transparent border p-0 d-flex justify-content-center'>";
        str += "<img class='img-fluid' src='"+book.images[0].path+"' alt='"+book.title+"' style='object-fit: contain'>";
        str += "</div>";

        //hien thi sach
        str += "<div class='card-body border-left border-right text-center pt-4 pb-3'>";
        str += "<h6 class='text-truncate text-danger mb-3'>" + book.title + "</h6>";
        str += "<div class='d-flex justify-content-center'>"
        str += "<h6 class='new-price'>" + book.discountPrice + "</h6>";
        str += "<p class='ml-2' style='text-decoration: line-through; font-size: smaller'>";
        str += book.discountPercent ? book.priceFormat : '';
        str += "</p>";
        str += "</div>";
        str += "</div>";

        //button chi tiet
        str += " <div class='card-footer d-flex justify-content-between bg-light border'>";
        str += "<a href='/chi-tiet?id=" + book.id + "' class='btn btn-sm text-dark p-0'><i class='fas fa-eye text-primary mr-1'></i>Chi tiết</a>";
        str += " <a onclick='addToCart(" + book.id + "," + 1 + ")' class='btn btn-sm text-dark p-0'><i class='fas fa-shopping-cart text-primary mr-1'></i>Giỏ hàng</a>";
        str += "</div>";
        str += "</div>";
        str += "</div>";

    }
    return str;
}

function showPagination(page, totalPage) {
    var str = "<ul class='pagination justify-content-center mb-3'>";

    //trang dau tien thi nut prev se disabled
    if (page == 1) {
        //button trang dau
        str += "<li class='page-item disabled'>";
        str += "<a class='page-link'>Trang đầu</a>";
        str += "</li>"
        //button mui ten
        str += "<li class='page-item disabled'>";
        str += "<a class='page-link' aria-label='previous'>";
        str += "<span aria-hidden='true'>&laquo;</span>";
        str += "<span class='sr-only'>Previous</span>";
        str += "</a>";
        str += "</li>"
    } else {
        //button trang dau
        str += "<li class='page-item'>";
        str += "<a class='page-link' onclick='movePage(" + 1 + ")'>Trang đầu</a>";
        str += "</li>"
        //button mui ten
        str += "<li class='page-item'>";
        str += "<a class='page-link' aria-label='previous' onclick='movePage(" + (page - 1) + "'>";
        str += "<span aria-hidden='true'>&laquo;</span>";
        str += "<span class='sr-only'>Previous</span>";
        str += "</a>";
        str += "</li>"
    }

    //hien thi cac nut pagination
    for (let i = 1; i <= totalPage; i++) {
        // neu i bang page hien tai thi cho nut do active
        if (i == page) {
            str += "<li class='page-item active'><a class='page-link'>" + i + "</a></li>";
        } else {
            //cac nut khac khong phai page hien tai thi khong active, them su click
            str += "<li class='page-item'><a class='page-link' onclick='movePage(" + i + ")'>" + i + "</a></li>";
        }
    }

    //nut cuoi cung
    if (page == totalPage) {
        //button mui ten
        str += "<li class='page-item disabled'>";
        str += "<a class='page-link' aria-label='next'>";
        str += "<span aria-hidden='true'>&raquo;</span>";
        str += "<span class='sr-only'>next</span>";
        str += "</a>";
        str += "</li>"
        //button trang dau
        str += "<li class='page-item disabled'>";
        str += "<a class='page-link'>Trang cuối</a>";
        str += "</li>"
    } else {
        //button mui ten
        str += "<li class='page-item'>";
        str += "<a class='page-link' aria-label='next' onclick='movePage(" + (page + 1) + ")'>";
        str += "<span aria-hidden='true'>&raquo;</span>";
        str += "<span class='sr-only'>next</span>";
        str += "</a>"
        str += "</li>"
        //button trang cuoi
        str += "<li class='page-item'>";
        str += "<a class='page-link' onclick='movePage(" + totalPage + ")'>Trang cuối</a>";
        str += "</li>"
    }
    str += "</ul>";
    return str;
}

//su kien sap xep
function sort() {
    var sortValue = $("#sortSelect").val();
    var searchValue = window.location.search;

    var sort = "";
    if (searchValue == "") {
        sort = sortValue;
    } else {
        sort = "&" + sortValue.substring(1);
    }
    var query = searchValue + sort;
    $.ajax({
        url: 'danh-sach-san-pham' + query, cache: false, dataType: "json", success: function (result) {
            show(result)
        }
    });
}

function searchTitle() {
    var searchValue = $("#search").val();
    $.ajax({
        url: "danh-sach-san-pham?title=" + searchValue, cache: false, dataType: "json", success: function (result) {
            show(result)
        }
    });
}

function filterByPrice() {
    var filterPrice = $("input[type=radio][name=filterByPrice]:checked").val();
    var searchValue = window.location.search;
    var filter = "";
    if (searchValue == "") {
        filter = filterPrice;
    } else {
        filter = "&" + filterPrice.substring(1);
    }
    var query = searchValue + filter;
    $.ajax({
        url: "danh-sach-san-pham" + query, cache: false, dataType: "json", success: function (result) {
            show(result)
        }
    });
}

//check for registation
function checkEmail() {
    var emailInput = $("#email").val();
    if (emailInput.length == 0) {
        $("#emailError").html("Vui lòng không bỏ trống email");
        $("#emailError").css({
            display: "block", color: "red", fontSize: 12
        });
        return false;
    } else {
        var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        if (emailInput.match(mailformat)) {
            $.ajax({
                method: 'GET', url: 'check-mail', cache: 'false', data: {
                    email: emailInput
                }, success: function (result) {
                    if (result == emailInput) {
                        $("#emailError").html("Email đã tồn tại. Vui lòng thử lại email khác");
                        $("#emailError").css({
                            display: "block", color: "red", fontSize: 12
                        });
                    } else {
                        $("#emailError").html("");
                        $("#emailError").css({
                            display: "none",
                        });
                    }
                }
            })
            try {
                return true;
            } catch (e) {
                return false;
            }
        } else {
            $("#emailError").html("Email không hợp lệ");
            $("#emailError").css({
                display: "block", color: "red", fontSize: 12
            });
            return false;
        }
    }
}

function checkUsername() {
    var userInput = $("input[type=text][name=username]").val();
    if (userInput.length == 0) {
        $("#userError").html("Vui lòng không bỏ trống tên người dùng");
        $("#userError").css({
            display: "block", color: "red", fontSize: 12
        });
        return false;
    } else {
        $("#userError").html("");
        $("#userError").css({
            display: "none"
        });
        return true;
    }
}

function checkPass() {
    var passInput = $("input[type=password][name=password]").val();
    if (passInput.length < 8) {
        $("#passError").html("Vui lòng nhập mật khẩu từ 8 ký tự trở lên");
        $("#passLoginError").html("Vui lòng nhập mật khẩu từ 8 ký tự trở lên");
        $("#passError").css({
            display: "block", color: "red", fontSize: 12
        });
        return false;
    } else {
        $("#passError").html("");
        $("#passLoginError").html("");
        $("#passError").css({
            display: "none"
        });
        return true;
    }
}

function checkRePassword() {
    var passInput = $("input[type=password][name=password]").val();
    var rePassInput = $("input[type=password][name=rePassword]").val();
    if (rePassInput.length == 0) {
        $("#rePassError").html("Vui lòng nhập lại mật khẩu");
        $("#rePassError").css({
            display: "block", color: "red", fontSize: 12
        });
        return false;
    } else {
        if (rePassInput != passInput) {
            $("#rePassError").html("Mật khẩu không khớp");
            $("#rePassError").css({
                display: "block", color: "red", fontSize: 12
            });
            return false;
        } else {
            $("#rePassError").html("");
            $("#rePassError").css({
                display: "none"
            });
            return true;
        }
    }
}

//user infor
function checkPhone() {
    var phoneInput = $("#phone").val();
    var regexPersonal = /(03|05|07|08|09)+([0-9]{8})\b/;
    if (!phoneInput.match(regexPersonal) || phoneInput.length < 10 || phoneInput.length > 11) $("#phoneError").html("Số điện thoại không hợp lệ");
    else $("#phoneError").html("");
}

function checkOldPass() {
    var oldPhoneInput = $("#oldPass").val();
    $.ajax({
        url: "kiem-tra-mat-khau",
        cache: false,
        data: {
            oldPassword: oldPhoneInput
        },
        success: function (isMatched) {
            if (isMatched) {
                $("#oldPassError").html("");
                return true;
            } else {
                $("#oldPassError").html("Mật khẩu cũ không chính xác");
                return false;
            }
        }
    });
}

//show alert add to cart success
function addToCart(bookId, quantity) {
    $.ajax({
        method: "get",
        url: "them-san-pham",
        cache: false,
        data: {
            bookID: bookId,
            quantity: quantity
        },
        dataType:"json",
        success: function (result) {
            if (result.user == null) {
                window.location.href="/dang-nhap"
            } else alert("Thêm sản phẩm vào giỏ hàng thành công");
        }
    })
}

function formatPrice(price) {
    price = price.toLocaleString('it-IT', {style: 'currency', currency: 'VND'});
    return price;
}