@import hexlet.code.dto.url.UrlsPage
@param UrlsPage page

@template.layout.page(
    content = @`
        <h1>Сайты</h1>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Имя</th>
                        <th>Последняя проверка</th>
                        <th>Код ответа</th>
                    </tr>
                </thead>
                <tbody>
                    @for(var url : page.getUrls())
                        <tr>
                            <td>
                                ${url.getId()}
                            </td>
                            <td>
                                ${url.getName()}
                            </td>
                            <td>
                                ${url.getLastCheck()}
                            </td>
                            <td>
                                ${url.getCheckCode()}
                            </td>
                        </tr>
                    @endfor
                </tbody>
            </table>
    `
)
