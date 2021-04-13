<#import 'layouts/base.ftl' as base>
<@base.mainMacro title="Избранное">
    <div id="wrapper" class="wide-wrap">
        <div class="offcanvas-overlay"></div>
        <div class="content-container">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="main-content">
                            <form class="commerce">
                                <div class="wishlist-title ">
                                    <h2>Избранное</h2>
                                </div>
                                <table class="shop_table cart wishlist_table">
                                    <thead>
                                    <tr>
                                        <th class="product-remove"></th>
                                        <th class="product-thumbnail"></th>
                                        <th class="product-name"><span class="nobr">Наименование</span></th>
                                        <th class="product-price"><span class="nobr">Цена</span></th>
                                        <th class="product-stock-stauts"><span class="nobr">Категория </span></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list products as product>
                                        <tr>
                                            <td class="product-remove">
                                                <a href="/wishlist/action/delete?id=${product.id}"
                                                   class="remove remove_from_wishlist">&times;</a>
                                            </td>
                                            <td class="product-thumbnail">
                                                <img width="100" height="150" src="/img?name=${product.imageName}"
                                                     alt="Product-1"/>
                                            </td>
                                            <td class="product-name">
                                                ${product.name}
                                            </td>
                                            <td class="product-price">
                                                <span class="amount">${product.price} Р</span>
                                            </td>
                                            <td class="product-stock-status">
                                                <span class="wishlist-in-stock">${product.category.name}</span>
                                            </td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <td colspan="6">&nbsp;</td>
                                    </tr>
                                    </tfoot>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@base.mainMacro>
