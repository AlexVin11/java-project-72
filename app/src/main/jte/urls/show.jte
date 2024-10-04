@import hexlet.code.dto.urls.UrlPage
@param UrlPage page

@template.layout.page(
content = @`
    <section>
        <div class="container-lg mt-5">
            <h1>Сайт: https://www.google.com</h1>
            <table class="table table-bordered table-hover mt-3">
                <tbody>
                <tr>
                    <td>ID</td>
                    <td>4</td>
                </tr>
                <tr>
                    <td>Имя</td>
                    <td>https://www.google.com</td>
                </tr>
                <tr>
                    <td>Дата создания</td>
                    <td>04/10/2024 14:47</td>
                </tr>
                </tbody>
            </table>
            <h2 class="mt-5">Проверки</h2>
            <form method="post" action="/urls/4/checks">
                <button type="submit" class="btn btn-primary">Запустить проверку</button>
            </form>
            <table class="table table-bordered table-hover mt-3">
                <thead>
                    <tr><th class="col-1">ID</th>
                    <th class="col-1">Код ответа</th>
                    <th>title</th>
                    <th>h1</th>
                    <th>description</th>
                    <th class="col-2">Дата проверки</th>
                </tr></thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </section>
`
)
