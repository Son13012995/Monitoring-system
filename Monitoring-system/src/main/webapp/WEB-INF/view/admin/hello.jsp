<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Avengers assemble" />
    <meta name="author" content="Son" />
    <title>Avengers assemble</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <style>
        /* Reset and Base Styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #1a1a1a;
            color: #ffffff;
            line-height: 1.6;
        }

        /* Header */
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 50px;
            background-color: #000;
        }

        header .logo {
            font-size: 24px;
            font-weight: bold;
            color: #00d4ff;
        }

        header .contact-info {
            font-size: 14px;
            color: #888;
        }

        header .contact-info a {
            color: #00d4ff;
            text-decoration: none;
        }

        header .menu {
            font-size: 24px;
            color: #888;
            cursor: pointer;
        }

        /* Hero Section */
        .hero {
            display: flex;
            align-items: center;
            padding: 50px;
            background-color: #222;
        }

        .hero img {
            width: 50%;
            border-radius: 10px;
        }

        .hero-text {
            padding-left: 50px;
            width: 50%;
        }

        .hero-text h1 {
            font-size: 48px;
            color: #00d4ff;
            margin-bottom: 20px;
        }

        .hero-text p {
            color: #ccc;
            margin-bottom: 20px;
        }

        .hero-text a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #ff007a;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }

        /* Services Section */
        .services {
            padding: 50px;
            display: flex;
            justify-content: space-between;
            gap: 20px;
        }

        .service-card {
            width: 30%;
            background-color: #333;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
        }

        .service-card .icon {
            width: 50px;
            height: 50px;
            margin: 0 auto 20px;
            background-color: #00d4ff;
            border-radius: 5px;
        }

        .service-card .icon.orange {
            background-color: #ff9500;
        }

        .service-card .icon.pink {
            background-color: #ff007a;
        }

        .service-card h3 {
            font-size: 24px;
            color: #00d4ff;
            margin-bottom: 10px;
        }

        .service-card p {
            color: #ccc;
        }

        /* Portfolio Section */
        .portfolio {
            padding: 50px;
        }

        .portfolio h2 {
            font-size: 36px;
            color: #00d4ff;
            text-align: center;
            margin-bottom: 30px;
        }

        .portfolio-gallery {
            display: flex;
            justify-content: space-between;
            gap: 20px;
        }

        .portfolio-item {
            width: 23%;
            text-align: center;
        }

        .portfolio-item img {
            width: 100%;
            border-radius: 10px;
            margin-bottom: 10px;
        }

        .portfolio-item p {
            color: #00d4ff;
            font-size: 18px;
        }
    </style>
</head>

<body>
    <!-- Header -->
    <header>
        <div class="logo">SSS Studio</div>
        <div class="contact-info">
            <span>+84 0918869195</span> | 
            <a href="mailto:23020699@gmail.com">23020699@gmail.com</a> | 
            <a href="#">Let's talk</a>
        </div>
        <div class="menu">
            <i class="fas fa-bars"></i>
        </div>
    </header>

    <!-- Hero Section -->
    <section class="hero">
        <img src="https://placehold.co/600x400/png" alt="Hero Image">
        <div class="hero-text">
            <h1>SSS Studio Fiction</h1>
            <p>...</p>
            <a href="#">Learn More</a>
        </div>
    </section>

    <!-- Services Section -->
    <section class="services">
        <div class="service-card">
            <div class="icon"></div>
            <h3>make something new</h3>
            <p></p>
        </div>
        <div class="service-card">
            <div class="icon orange"></div>
            <h3>Creative Concepts</h3>
            <p>Innovative ideas to make your projects stand out in a crowded digital space.</p>
        </div>
        <div class="service-card">
            <div class="icon pink"></div>
            <h3>Shooting & Editing</h3>
            <p>Professional video shooting and editing services for a polished final product.</p>
        </div>
    </section>

    <!-- Portfolio Section -->
    <section class="portfolio">
        <h2>Our Work</h2>
        <div class="portfolio-gallery">
            <div class="portfolio-item">
                <img src="https://placehold.co/600x400" alt="Portfolio 1">
                <p>placeholder</p>
            </div>
        </div>
    </section>
</body>

</html>