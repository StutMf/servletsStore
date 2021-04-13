<div id="wrapper" class="wide-wrap">
    <header class="header-container header-type-classic header-navbar-classic header-scroll-resize">
        <div class="navbar-container">
            <div class="navbar navbar-default navbar-scroll-fixed">
                <div class="navbar-default-wrap">
                    <div class="container">
                        <div class="row">
                            <div class="navbar-default-col">
                                <div class="navbar-wrap">
                                    <div class="navbar-header">
                                        <a class="navbar-brand" href="/index">
                                            <h2>ServletWebStore</h2>
                                        </a>
                                    </div>
                                    <#if user?? && user.role=="ADMIN">
                                        <nav class="collapse navbar-collapse primary-navbar-collapse">
                                            <ul class="nav navbar-nav primary-nav sf-js-enabled sf-arrows">
                                                <li><a href="/product/action/save"><span class="underline">Добавить товар</span></a>
                                                </li>
                                                <li><a href="/category/action/save"><span class="underline">Добавить Категорию</span></a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </#if>
                                    <div class="header-right">
                                        <div class="navbar-wishlist">
                                            <a class="wishlist" href="/wishlist">
                                                <i class="fa fa-heart-o"></i>
                                            </a>
                                        </div>
                                        <#if user??>
                                            <div class="navbar-minicart navbar-minicart-topbar">
                                                <div class="navbar-minicart">
                                                    <a class="minicart-link" href="/logout">
                                                        <span class="minicart-icon">
                                                            <i class="fas fa-sign-out-alt"></i>
                                                        </span>
                                                    </a>
                                                </div>
                                            </div>
                                            <#else>
                                                <div class="navbar-minicart navbar-minicart-topbar">
                                                    <div class="navbar-minicart">
                                                        <a class="minicart-link" href="/signIn">
                                                            <span class="minicart-icon">
                                                                <i class="fas fa-user-plus"></i>
                                                            </span>
                                                        </a>
                                                    </div>
                                                </div>
                                        </#if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="header-search-overlay hide">
                    <div class="container">
                        <div class="header-search-overlay-wrap">
                            <form class="searchform">
                                <input type="search" class="searchinput" name="s" autocomplete="off" value=""
                                       placeholder="Поиск..."/>
                            </form>
                            <button type="button" class="close">
                                <span aria-hidden="true" class="fa fa-times"></span>
                                <span class="sr-only">Close</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
</div>