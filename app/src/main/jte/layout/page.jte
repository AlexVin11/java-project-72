@import gg.jte.Content
@param Content content

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- Взято со странички по установке Boostrap на хекслет -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
        crossorigin="anonymous">
    <title>Анализатор страниц</title>
    </head>
    <body>
        <header>
            <nav id="menu">
                <ul>
                    <li class="navbar-brand"><a href="/"></a>Анализатор страниц</li>
                    <li class="main"><a href="/"></a>Главная</li>
                    <li class="main"><a href="/urls"></a>Сайты</li>
                </ul>
            </nav>
        </header>
        <main>
            ${content}
        </main>
    </body>
</html>
