@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.dto.BasePage
@param UrlsPage page
@param BasePage basepage

@template.layout.page(
    content = @`
        <section>
            @if(basepage.getFlash() != null)
                <div class="rounded-0 m-0 alert alert-dismissible fade show alert-success" role="alert">
                    <p class="m-0">${basepage.getFlash()}</p>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            @endif
                <div class="container-lg mt-5">
                    <h1>Сайты</h1>
                        <table class="table table-bordered table-hover mt-3">
                            <thead>
                                <tr>
                                    <th class="col-1">ID</th>
                                    <th>Имя</th>
                                    <th class="col-2">Последняя проверка</th>
                                    <th class="col-1">Код ответа</th>
                                </tr>
                            </thead>
                            <tbody>
                                @for(var url : page.getUrls())
                                    <tr>
                                        <td>
                                            ${url.getId()}
                                        </td>
                                        <td>
                                            <a href="/urls/${url.getId()}">${url.getName()}</a>
                                        </td>
                                        <td>
                                            пока что тут ничего
                                            <!-- ${url.getLastCheck()} -->
                                        </td>
                                        <td>
                                            пока что тут ничего
                                            <!-- ${url.getCheckCode()} -->
                                        </td>
                                    </tr>
                                @endfor
                            </tbody>
                        </table>
                </div>
        </section>
    `
)
