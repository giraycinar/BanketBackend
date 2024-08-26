<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BilgeAdam Banket</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        }

        .header {
            background-color: #007bff;
            color: #fff;
            padding: 20px;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
        }

        .header h1 {
            text-align: center;
        }

        .content {
            padding: 20px;
            color: #333;
            text-align: center;
        }

        h2 {
            margin-bottom: 10px;
            font-weight: bold;
            color: #007bff;
        }

        h3 {
            margin-bottom: 10px;
            font-weight: bold;
            color: #333;
        }

        p {
            margin-bottom: 8px;
            color: #666;
        }

        button {
            padding: 0.75rem 1.25rem;
            font-size: 1rem;
            background-color: #4caf50;
            color: white;
            border: none;
            border-radius: 0.25rem;
            cursor: pointer;
        }

        hr {
            border: none;
            border-top: 1px solid #ccc;
            margin: 20px 0;
        }

        .footer {
            background-color: #007bff;
            color: #fff;
            padding: 8px 20px;
            text-align: center;
            border-bottom-left-radius: 8px;
            border-bottom-right-radius: 8px;
        }

        .footer p{
            color: #fff;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1 style="font-size: 24px; margin: 0;">BilgeAdam Banket Application</h1>
    </div>
    <div class="content">
        <h1>Merhaba ${name} ${surname}! </h1>
        <h3>BilgeAdam Banket uygulaması sistemine kaydınız yapılmıştır.</h3>
        <p>Aşağıda verilen email ve şifre ile Banket uygulamasına giriş yapabilirsiniz.</p>
        <p><strong>Email:</strong> ${email}</p>
        <p><strong>Şifre:</strong> ${password}</p>
        <a href="http://localhost:5173" target="_blank">
            <button>Banket Giriş Sayfası</button>
        </a>
    </div>
    <div class="footer">
        <p>BilgeAdam Java12 Group Banket Project - 	&#169; Copyright 2024</p>
    </div>
</div>
</body>
</html>
