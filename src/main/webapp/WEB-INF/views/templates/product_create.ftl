<#import 'layouts/base.ftl' as base>
<@base.mainMacro title="Создание товара">
    <main>
        <div class="container">
            <section class="mt-5 mb-4">
                <div class="row">
                    <div class="col"></div>
                    <div class="col-8 align-self-center">
                        <div class="card wish-list mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Новый товар</h3>
                                    <#if error??>
                                        <h4 class="error-message" id="error-message">${error}</h4>
                                    </#if>
                                    <h4 class="error-message" id="error-message"></h4>
                                    <form action="/product/action/save" autocomplete="off" method="post" id="form" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <span class="text-black-50">Название</span>
                                            <input id="email-signup" type="text"
                                                   class="form-control" name="name"
                                                   required>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Цена</span>
                                            <input id="firstname-signup" type="number" class="form-control" name="price"
                                                   required>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Категория</span>
                                            <select class="form-control" name="category">
                                                <#list categories as category>
                                                    <option value="${category.id}">${category.name}</option>
                                                </#list>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Фото товара</span>
                                                <input type="file" class="form-control-file" name="image" accept="image/*">
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