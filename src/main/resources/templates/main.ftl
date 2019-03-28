

<#import "parts/common.ftl" as c>
<@c.page "InterShop" "">
<script src="/js/searchMain.js" type="text/javascript" ></script>
<style>
    .catdiv{
        overflow-x: auto;
        overflow-y: hidden;
    }
</style>
<div class="row">
    <div class="col">
        <div class="row mt-3 ml-2 mr-2">
            <div class="catdiv">
                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                    <label class="btn btn-secondary active">
                        <input type="radio" name="options" value="0" id="option1" autocomplete="off" checked> None
                    </label>
                    <#list categories as category>
                        <label class="btn btn-secondary">
                            <input type="radio" name="options" value="${category.id}" autocomplete="off"> ${category.categoryName}
                        </label>
                    </#list>
                </div>
            </div>
        </div>
        <div class="row mt-3 ml-3 mr-3">
            <div class="col">
                <div class="row">
                    <div class="col">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Search items..." id="keyword" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" onclick="search()">Search</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="btn btn-outline-secondary">Min $</span>
                            </div>
                            <input type="text" class="form-control" id="minPrice" aria-label="Amount (to the nearest dollar)" onkeyup="isValidPrice('minPrice')" placeholder="0.00">
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="btn btn-outline-secondary">Max $</span>
                            </div>
                            <input type="text" class="form-control" id="maxPrice" aria-label="Amount (to the nearest dollar)" onkeyup="isValidPrice('maxPrice')" placeholder="0.00">
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="btn btn-outline-secondary" for="inputGroupSelect01">Sort</label>
                            </div>
                            <select class="custom-select" id="inputGroupSelect01">
                                <option value="0" selected>None</option>
                                <option value="1">Most viewed</option>
                                <option value="2">Most commented</option>
                                <option value="3">Price low to high</option>
                                <option value="4">Price high to low</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<div class="row">
<div class="col">

<div>Announcements:</div>
    <#list announcements as announcement>
        <div>
            <br/>
            <img width="100" height="100" src="${announcement.product.photoURL}" /> <br/>
            <a href="${announcement.productURL}">${announcement.product.title}</a> <br/>
            <i>${announcement.product.getCategory()}</i> <br/>
            <i>${announcement.currency} <b>${announcement.price}</b></i> <br/>
            <br/>
        </div>
    <#else>
    No announcements.
    </#list>
</div>
</div>
</@c.page>