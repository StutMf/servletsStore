<#import 'layouts/base.ftl' as base>
<@base.mainMacro title="Главная">
    <div id="wrapper" class="wide-wrap">
        <div class="offcanvas-overlay"></div>
        <div class="content-container commerce page-layout-left-sidebar">
            <div class="container">
                <div class="row">
                    <div class="col-md-9 main-wrap">
                        <div class="main-content">
                            <div class="shop-toolbar"></div>
                            <div class="shop-loop grid">
                                <ul class="products">
                                    <#if products??>
                                        <#list products as product>
                                            <li class="product col-md-3 col-sm-6">
                                                <div class="product-container">
                                                    <figure>
                                                        <div class="product-wrap">
                                                            <div class="product-images">
                                                                <div class="shop-loop-thumbnail shop-loop-front-thumbnail">
                                                                    <img width="375" height="505"
                                                                         src="/img?name=${product.imageName}"
                                                                         alt=""/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <figcaption>
                                                            <div class="shop-loop-product-info">
                                                                <div class="info-meta clearfix">
                                                                    <div class="star-rating">
                                                                        <span style="width: 100%;">${product.category.name}</span>
                                                                    </div>
                                                                    <div class="loop-add-to-wishlist">
                                                                        <div class="yith-wcwl-add-to-wishlist">
                                                                            <div class="yith-wcwl-add-button">
                                                                                <#if user?? && user.role=="ADMIN">
                                                                                    <a href="/product/action/delete?id=${product.id}"
                                                                                       class="delete_product">
                                                                                        Удалить X
                                                                                    </a>
                                                                                <#else>
                                                                                    <a href="/wishlist/action/add?id=${product.id}"
                                                                                       class="add_to_wishlist">
                                                                                        Add to Wishlist
                                                                                    </a>
                                                                                </#if>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="info-content-wrap">
                                                                    <h3 class="product_title">
                                                                        <a href="shop-detail-1.html">${product.name}</a>
                                                                    </h3>
                                                                    <div class="info-price">
																	<span class="price">
																		<span class="amount">
																			${product.price} Р
																		</span>
																	</span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </figcaption>
                                                    </figure>
                                                </div>
                                            </li>
                                        </#list>
                                    </#if>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 sidebar-wrap">
                        <form action="/index" method="get">
                            <div class="main-sidebar">
                                <div class="widget widget_product_categories">
                                    <h4 class="widget-title"><span>Категории</span></h4>
                                    <ul class="product-categories">
                                        <#if categories??>
                                            <#list categories as category>
                                                <li>
                                                    <div class="form-check">
                                                        <label class="form-check-label">
                                                            <input class="form-check-input" type="radio" name="category"
                                                                   value="${category.id}">
                                                            ${category.name}
                                                            <#if user?? && user.role=="ADMIN">
                                                                <a href="/category/action/delete?id=${category.id}"
                                                                   class="delete_product">
                                                                    Удалить X
                                                                </a>
                                                            </#if>
                                                        </label>
                                                    </div>
                                                </li>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div>
                                <#if user??>
                                    <div class="widget widget_product_categories">
                                        <h4 class="widget-title"><span>Избранное</span></h4>
                                        <ul class="product-categories">
                                            <li>
                                                <div class="form-check">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" name="wishlist"
                                                               value="category_id">
                                                        Избранное ?
                                                    </label>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </#if>
                                <button class="btn btn-primary btn-lg btn-block waves-effect waves-light" type="submit">Показать</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">

        var tpj = jQuery;

        var revapi7;
        tpj(document).ready(function () {
            if (tpj("#rev_slider").revolution == undefined) {
                revslider_showDoubleJqueryError("#rev_slider");
            } else {
                revapi7 = tpj("#rev_slider").show().revolution({
                    sliderType: "standard",
                    sliderLayout: "fullwidth",
                    dottedOverlay: "none",
                    delay: 9000,
                    navigation: {
                        keyboardNavigation: "off",
                        keyboard_direction: "horizontal",
                        mouseScrollNavigation: "off",
                        onHoverStop: "on",
                        touch: {
                            touchenabled: "on",
                            swipe_threshold: 75,
                            swipe_min_touches: 50,
                            swipe_direction: "horizontal",
                            drag_block_vertical: false
                        }
                        ,
                        arrows: {
                            style: "gyges",
                            enable: true,
                            hide_onmobile: true,
                            hide_under: 600,
                            hide_onleave: true,
                            hide_delay: 200,
                            hide_delay_mobile: 1200,
                            tmp: '',
                            left: {
                                h_align: "left",
                                v_align: "center",
                                h_offset: 30,
                                v_offset: 0
                            },
                            right: {
                                h_align: "right",
                                v_align: "center",
                                h_offset: 30,
                                v_offset: 0
                            }
                        }
                        ,
                        bullets: {
                            enable: true,
                            hide_onmobile: true,
                            hide_under: 600,
                            style: "hephaistos",
                            hide_onleave: true,
                            hide_delay: 200,
                            hide_delay_mobile: 1200,
                            direction: "horizontal",
                            h_align: "center",
                            v_align: "bottom",
                            h_offset: 0,
                            v_offset: 30,
                            space: 5,
                            tmp: ''
                        }
                    },
                    gridwidth: 1170,
                    gridheight: 600,
                    lazyType: "smart",
                    parallax: {
                        type: "mouse",
                        origo: "slidercenter",
                        speed: 2000,
                        levels: [2, 3, 4, 5, 6, 7, 12, 16, 10, 50],
                    },
                    shadow: 0,
                    spinner: "off",
                    stopLoop: "off",
                    stopAfterLoops: -1,
                    stopAtSlide: -1,
                    shuffle: "off",
                    autoHeight: "off",
                    disableProgressBar: "on",
                    hideThumbsOnMobile: "off",
                    hideSliderAtLimit: 0,
                    hideCaptionAtLimit: 0,
                    hideAllCaptionAtLilmit: 0,
                    startWithSlide: 0,
                    debugMode: false,
                    fallbacks: {
                        simplifyAll: "off",
                        nextSlideOnWindowFocus: "off",
                        disableFocusListener: false,
                    }
                });
            }
        });
    </script>
</@base.mainMacro>