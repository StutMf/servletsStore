<#import 'layouts/base.ftl' as base>
<@base.mainMacro title="Создание категории">
    <main>
        <div class="container">
            <section class="mt-5 mb-4">
                <div class="row">
                    <div class="col"></div>
                    <div class="col-8 align-self-center">
                        <div class="card wish-list mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Новая категория</h3>
                                    <h4 class="error-message" id="error-message"></h4>
                                    <form action="/category/action/save" autocomplete="off" method="post" id="form">
                                        <div class="form-group">
                                            <span class="text-black-50">Название</span>
                                            <input id="email-signup" type="text"
                                                   class="form-control" name="name"
                                                   required>
                                        </div>
                                        <button class="btn btn-primary btn-lg btn-block waves-effect waves-light"
                                                id="submit"> Создать
                                        </button>
                                    </form>
                                    <hr>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col"></div>
                </div>
            </section>
        </div>
    </main>
</@base.mainMacro>