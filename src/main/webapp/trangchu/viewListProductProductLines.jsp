<%--
  Created by IntelliJ IDEA.
  User: MSII
  Date: 07/10/2020
  Time: 9:38 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ogani | Template</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="/csstrangchu/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/csstrangchu/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/csstrangchu/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="/csstrangchu/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="/csstrangchu/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="/csstrangchu/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="/csstrangchu/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="/csstrangchu/css/style.css" type="text/css">

</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>
<!-- The Modal -->
<div class="modal" id="myModal">
    <form action="/user?action=customer" method="post">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Login</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <label for="userName">User Name:</label><input type="text" class="form-control" id="userName" name="userName">
                    <label for="password">Password:</label><input type="password" class="form-control" id="password" name="password">
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Login</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- The Modal đăng kí -->
<div class="modal" id="myModaldk">
    <form action="/user" method="post">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">DANG KI</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal body -->
                <div class="modal-body">
                    <label for="userName">User Name:</label><input type="text" class="form-control" id="name" name="name" required >
                    <label for="password">Password:</label><input type="password" class="form-control" id="pas" name="pass" required>
                    <label for="email">Email:</label><input type="email" class="form-control" id="email" name="email" required>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Create</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </form>
</div>

<!-- Humberger Begin -->
<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
    <div class="humberger__menu__logo">
        <a href="/TrangChu"><img src="/csstrangchu/img/logo.png" alt=""></a>
    </div>
    <div class="humberger__menu__cart">
        <ul>
            <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
            <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
        </ul>
        <div class="header__cart__price">item: <span>$150.00</span></div>
    </div>
    <div class="humberger__menu__widget">
        <div class="header__top__right__language">
            <img src="/csstrangchu/img/language.png" alt="">
            <div>English</div>
            <span class="arrow_carrot-down"></span>
            <ul>
                <li><a href="#">Tiếng Việt</a></li>
                <li><a href="#">English</a></li>
            </ul>
        </div>
        <div class="header__top__right__auth">
            <c:if test="${user!=null}">
                <a href="/user?action=loginOut"><i class="fa fa-user"> ${user.userName}</i> Logout</a>
            </c:if>
            <c:if test="${user==null}">
                <a href="#" data-toggle="modal" data-target="#myModal"><i class="fa fa-user"></i> Login</a>
            </c:if>
        </div>
        <div class="header__top__right__auth">
            <c:if test="${user==null}">
                <a href="#" data-toggle="modal" data-target="#myModaldk"> Dangki</a>
            </c:if>
        </div>
    </div>
    <nav class="humberger__menu__nav mobile-menu">
        <ul>
            <li class="active"><a href="/TrangChu">Home</a></li>
            <li><a href="/TrangChu?action=shop">Shop</a></li>
            <li><a href="#">ProductLines</a>
                <ul class="header__menu__dropdown">
                    <c:forEach items="${tenTheLoai}" var="tenTheLoai">
                        <li><a href="/TrangChu?action=productLine&tenTheLoai=${tenTheLoai}" >${tenTheLoai}</a></li>
                    </c:forEach>
                </ul>
            </li>
            <li><a href="/TrangChu?action=blog">Blog</a></li>
            <li><a href="/TrangChu?action=contact">Contact</a></li>
        </ul>
    </nav>
    <div id="mobile-menu-wrap"></div>
    <div class="header__top__right__social">
        <a href="#"><i class="fa fa-facebook"></i></a>
        <a href="#"><i class="fa fa-twitter"></i></a>
        <a href="#"><i class="fa fa-linkedin"></i></a>
        <a href="#"><i class="fa fa-pinterest-p"></i></a>
    </div>
    <div class="humberger__menu__contact">
        <ul>
            <li><i class="fa fa-envelope"></i> Volong24071995@gmail.com</li>
            <li>Free Shipping for all Order of $99</li>
        </ul>
    </div>
</div>
<!-- Humberger End -->

<!-- Header Section Begin -->
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="header__top__left">
                        <ul>
                            <li><i class="fa fa-envelope"></i> Volong24071995@gmail.com</li>
                            <li>Free Shipping for all Order of $99</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="header__top__right">
                        <div class="header__top__right__social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-linkedin"></i></a>
                            <a href="#"><i class="fa fa-pinterest-p"></i></a>
                        </div>
                        <div class="header__top__right__language">
                            <img src="/csstrangchu/img/language.png" alt="">
                            <div>English</div>
                            <span class="arrow_carrot-down"></span>
                            <ul>
                                <li><a href="#">Tiếng Việt</a></li>
                                <li><a href="#">English</a></li>
                            </ul>
                        </div>
                        <div class="header__top__right__auth">
                            <c:if test="${user!=null}">
                                <a href="/user?action=loginOut"><i class="fa fa-user"> ${user.userName}</i> Logout</a>
                            </c:if>
                            <c:if test="${user==null}">
                                <a href="#" data-toggle="modal" data-target="#myModal"><i class="fa fa-user"></i> Login</a>
                            </c:if>
                        </div>
                        <div class="header__top__right__auth">
                            <c:if test="${user==null}">
                                <a href="#" data-toggle="modal" data-target="#myModaldk"> Dangki</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="header__logo">
                    <a href="/TrangChu"><img src="/csstrangchu/img/logo.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6">
                <nav class="header__menu">
                    <ul>
                        <li><a href="/TrangChu">Home</a></li>
                        <li class="active"><a href="/TrangChu?action=shop">Shop</a></li>
                        <li><a href="#">ProductLines</a>
                            <ul class="header__menu__dropdown">
                                <c:forEach items="${tenTheLoai}" var="tenTheLoai">
                                    <li><a href="/TrangChu?action=productLine&tenTheLoai=${tenTheLoai}" >${tenTheLoai}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                        <li><a href="/TrangChu?action=blog">Blog</a></li>
                        <li><a href="/TrangChu?action=contact">Contact</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3">
                <div class="header__cart">
                    <ul>
                        <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                        <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
                    </ul>
                    <div class="header__cart__price">item: <span>$150.00</span></div>
                </div>
            </div>
        </div>
        <div class="humberger__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>
<!-- Header Section End -->

<!-- Hero Section Begin -->
<section class="hero hero-normal">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
            </div>
            <div class="col-lg-9">
                <div class="hero__search">
                    <div class="hero__search__form">
                        <form action="/TrangChu?action=search" method="post">
                            <input type="text" placeholder="What do you need?" name="name">
                            <button type="submit" class="site-btn">SEARCH</button>
                        </form>
                    </div>
                    <div class="hero__search__phone">
                        <div class="hero__search__phone__icon">
                            <i style="margin-top: 15px" class="fa fa-phone"></i>
                        </div>
                        <div class="hero__search__phone__text">
                            <h5>037.606.0151</h5>
                            <span>support 24/7 time</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Hero Section End -->

<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg" data-setbg="/csstrangchu/img/breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>${representative.tenSanPham}</h2>
                    <div class="breadcrumb__option">
                        <a href="/TrangChu?action=TrangChu">Home</a>
                        <a href="/TrangChu?action=productLine&tenTheLoai=${representative.loaiSanPham.tenTheLoai}">${representative.loaiSanPham.tenTheLoai}</a>
                        <span>${representative.tenSanPham}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Product Details Section Begin -->
<section class="product-details spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6">
                <div class="product__details__pic">
                    <div class="product__details__pic__item">
                        <img class="product__details__pic__item--large" src="${representative.image}" alt="image">
                    </div>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <div class="product__details__text">
                    <h3>${representative.tenSanPham}</h3>
                    <div class="product__details__rating">
                        <span>Số lượng đã bán: ${representative.soLuongMua}</span>
                    </div>
                    <div class="product__details__price">$${representative.giaSanPham}</div>
                    <p>Mô tả sản phẩm</p>
                    <div class="product__details__quantity">
                        <div class="quantity">
                            <div class="pro-qty">
                                <input type="text" value="1">
                            </div>
                            <a href="#" class="primary-btn">ADD TO CARD</a>
                            <a href="#" class="heart-icon"><span class="icon_heart_alt"></span></a>
                        </div>
                    </div>
                    <ul>
                        <li><b>Availability</b> <span>In Stock</span></li>
                        <li><b>Shipping</b> <span>01 day shipping. <samp>Free pickup today</samp></span></li>
                        <li><b>Share on</b>
                            <div class="share">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-instagram"></i></a>
                                <a href="#"><i class="fa fa-pinterest"></i></a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="product__details__tab">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab" aria-selected="true">Description</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab" aria-selected="false">Information</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#tabs-3" role="tab" aria-selected="false">Reviews <span>(1)</span></a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tabs-1" role="tabpanel">
                            <div class="product__details__tab__desc">
                                <h6>Products Infomation</h6>
                                <p>${representative.moTa}</p>
                            </div>
                        </div>
                        <div class="tab-pane" id="tabs-2" role="tabpanel">
                            <div class="product__details__tab__desc">
                                <h6>Products Infomation</h6>
                                <p>${representative.tenSanPham}</p>
                                <p>${representative.moTa}</p>
                                <p>Ngày sản xuất: ${representative.ngaySanXuat}</p>
                                <p>Hạn sử dụng: ${representative.hanSudung}</p>
                                <p>${representative.nhaSanXuat.tenNhaSanXuat}</p>
                            </div>
                        </div>
                        <div class="tab-pane" id="tabs-3" role="tabpanel">
                            <div class="product__details__tab__desc">
                                <h6>Products Infomation</h6>
                                <p>Tuyệt vời</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Product Details Section End -->

<!-- Related Product Section Begin -->
<section class="categories">
    <div class="container">
        <div class="row">
            <div class="section-title">
                <h2>List of product</h2>
            </div>
            <div class="categories__slider owl-carousel">
                <c:forEach items="${productList}" var="productList">
                <div class="col-lg-3">
                    <div class="categories__item set-bg" data-setbg="${productList.image}">
                        <h5><a href="/TrangChu?action=view&id=${productList.maSanPham}">${productList.tenSanPham}</a></h5>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
<!-- Related Product Section End -->
<section class="product-details spad">
</section>
<!-- Footer Section Begin -->
<footer class="footer spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="footer__about">
                    <div class="footer__about__logo">
                        <a href="/TrangChu"><img src="/csstrangchu/img/logo.png" alt=""></a>
                    </div>
                    <ul>
                        <li>Address: 28 Nguyễn Tri Phương TP.Huế</li>
                        <li>Phone: 037.606.0151</li>
                        <li>Email: Volong24071995@gmail.com</li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
                <div class="footer__widget">
                    <h6>Useful Links</h6>
                    <ul>
                        <li><a href="#">About Us</a></li>
                        <li><a href="#">About Our Shop</a></li>
                        <li><a href="#">Secure Shopping</a></li>
                        <li><a href="#">Delivery infomation</a></li>
                        <li><a href="#">Privacy Policy</a></li>
                        <li><a href="#">Our Sitemap</a></li>
                    </ul>
                    <ul>
                        <li><a href="#">Who We Are</a></li>
                        <li><a href="#">Our Services</a></li>
                        <li><a href="#">Projects</a></li>
                        <li><a href="#">Contact</a></li>
                        <li><a href="#">Innovation</a></li>
                        <li><a href="#">Testimonials</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-4 col-md-12">
                <div class="footer__widget">
                    <h6>Join Our Newsletter Now</h6>
                    <p>Get E-mail updates about our latest shop and special offers.</p>
                    <form action="#">
                        <input type="text" placeholder="Enter your mail">
                        <button type="submit" class="site-btn">Subscribe</button>
                    </form>
                    <div class="footer__widget__social">
                        <a href="#"><i style="margin-top: 12px" class="fa fa-facebook"></i></a>
                        <a href="#"><i style="margin-top: 12px" class="fa fa-instagram"></i></a>
                        <a href="#"><i style="margin-top: 12px" class="fa fa-twitter"></i></a>
                        <a href="#"><i style="margin-top: 12px" class="fa fa-pinterest"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="footer__copyright">
                    <div class="footer__copyright__text">
                        <p>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;
                            <script>
                                document.write(new Date().getFullYear());
                            </script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </p>
                    </div>
                    <div class="footer__copyright__payment"><img src="/csstrangchu/img/payment-item.png" alt=""></div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- Footer Section End -->

<!-- Js Plugins -->
<script src="/csstrangchu/js/jquery-3.3.1.min.js"></script>
<script src="/csstrangchu/js/bootstrap.min.js"></script>
<script src="/csstrangchu/js/jquery.nice-select.min.js"></script>
<script src="/csstrangchu/js/jquery-ui.min.js"></script>
<script src="/csstrangchu/js/jquery.slicknav.js"></script>
<script src="/csstrangchu/js/mixitup.min.js"></script>
<script src="/csstrangchu/js/owl.carousel.min.js"></script>
<script src="/csstrangchu/js/main.js"></script>


</body>

</html>
