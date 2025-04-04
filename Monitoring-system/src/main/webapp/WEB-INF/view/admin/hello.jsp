<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Avengers assemble">
    <meta name="author" content="Son">
    <title>Avengers assemble</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        'primary': '#00d4ff',
                        'secondary': '#ff007a',
                        'accent': '#ff9500',
                        'dark': '#1a1a1a',
                        'darker': '#222222',
                        'darkest': '#000000',
                    }
                }
            }
        }
    </script>
</head>

<body class="bg-dark text-white font-sans leading-relaxed">
    <!-- Header -->
    <header class="flex justify-between items-center px-12 py-5 bg-darkest">
        <div class="text-2xl font-bold text-primary">SSS Studio</div>
        <div class="text-sm text-gray-400">
            <span>+84 0918869195</span> | 
            <a href="mailto:23020699@gmail.com" class="text-primary no-underline">23020699@gmail.com</a> | 
            <a href="#" class="text-primary no-underline">Let's talk</a>
        </div>
        <div class="text-2xl text-gray-400 cursor-pointer">
            <i class="fas fa-bars"></i>
        </div>
    </header>

    <!-- Hero Section -->
    <section class="flex items-center p-12 bg-darker">
        <img src="/api/placeholder/600/400" alt="Hero Image" class="w-1/2 rounded-lg">
        <div class="pl-12 w-1/2">
            <h1 class="text-5xl text-primary mb-5">SSS Studio Fiction</h1>
            <p class="text-gray-300 mb-5">WE ARE A FAMILY</p>
            <a href="#" class="inline-block px-5 py-2 bg-secondary text-white no-underline rounded hover:bg-opacity-80 transition-all">Learn More</a>
        </div>
    </section>

    <!-- Services Section -->
    <section class="p-12 flex justify-between gap-5">
        <div class="w-1/3 bg-gray-800 p-5 rounded-lg text-center">
            <div class="w-12 h-12 mx-auto mb-5 bg-primary rounded"></div>
            <h3 class="text-2xl text-primary mb-2">Make Something New</h3>
            <p class="text-gray-300">WE GATHER TOGETHER</p>
        </div>
        <div class="w-1/3 bg-gray-800 p-5 rounded-lg text-center">
            <div class="w-12 h-12 mx-auto mb-5 bg-accent rounded"></div>
            <h3 class="text-2xl text-primary mb-2">Creative Concepts</h3>
            <p class="text-gray-300">Innovative ideas</p>
        </div>
        <div class="w-1/3 bg-gray-800 p-5 rounded-lg text-center">
            <div class="w-12 h-12 mx-auto mb-5 bg-secondary rounded"></div>
            <h3 class="text-2xl text-primary mb-2">Shooting & Editing</h3>
            <p class="text-gray-300">Professional video shooting and editing </p>
        </div>
    </section>

    <!-- Portfolio Section -->
    <section class="p-12">
        <h2 class="text-4xl text-primary text-center mb-8">Our Work</h2>
        <div class="flex justify-between gap-5">
            <div class="w-1/4 text-center">
                <img src="/api/placeholder/600/400" alt="Portfolio 1" class="w-full rounded-lg mb-2">
                <p class="text-lg text-primary">Project Showcase</p>
            </div>
            <div class="w-1/4 text-center">
                <img src="/api/placeholder/600/400" alt="Portfolio 2" class="w-full rounded-lg mb-2">
                <p class="text-lg text-primary">Video Production</p>
            </div>
            <div class="w-1/4 text-center">
                <img src="/api/placeholder/600/400" alt="Portfolio 3" class="w-full rounded-lg mb-2">
                <p class="text-lg text-primary">Creative Design</p>
            </div>
            <div class="w-1/4 text-center">
                <img src="/api/placeholder/600/400" alt="Portfolio 4" class="w-full rounded-lg mb-2">
                <p class="text-lg text-primary">Brand Identity</p>
            </div>
        </div>
    </section>

    <!-- Contact Section -->
    <section class="p-12 bg-darker">
        <h2 class="text-4xl text-primary text-center mb-8">Get In Touch</h2>
        <div class="max-w-2xl mx-auto">
            <form class="space-y-4">
                <div class="grid grid-cols-2 gap-4">
                    <input type="text" placeholder="Your Name" class="p-3 bg-gray-800 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary">
                    <input type="email" placeholder="Your Email" class="p-3 bg-gray-800 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary">
                </div>
                <input type="text" placeholder="Subject" class="w-full p-3 bg-gray-800 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary">
                <textarea placeholder="Your Message" rows="4" class="w-full p-3 bg-gray-800 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"></textarea>
                <button type="submit" class="w-full py-3 bg-secondary text-white rounded-lg hover:bg-opacity-80 transition-all">Send Message</button>
            </form>
        </div>
    </section>

    <!-- Footer -->
    <footer class="p-12 bg-darkest text-center text-gray-400">
        <div class="mb-4">
            <a href="#" class="text-primary mx-2 hover:text-white transition-colors">
                <i class="fab fa-facebook fa-lg"></i>
            </a>
            <a href="#" class="text-primary mx-2 hover:text-white transition-colors">
                <i class="fab fa-twitter fa-lg"></i>
            </a>
            <a href="#" class="text-primary mx-2 hover:text-white transition-colors">
                <i class="fab fa-instagram fa-lg"></i>
            </a>
            <a href="#" class="text-primary mx-2 hover:text-white transition-colors">
                <i class="fab fa-linkedin fa-lg"></i>
            </a>
        </div>
        <p>&copy; 2025 SSS Studio. All rights reserved.</p>
    </footer>
</body>
</html>