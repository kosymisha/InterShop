<script src="/js/searchProduct.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<div id="productForm">
    <div class="row">
        <div class="col-4">
            <button class="btn btn-secondary" onclick="backToAllProducts()" >Back to all products</button>
                <object name="objectsProd">
                    <div class="card m-2">
                        <img width="300" src="${product.photoURL}" /><br/>
                        <div>${product.title}</div>
                        <div>${product.category.categoryName}</div>
                    </div>
                </object>
        </div>
        <div class="col-8">
            <label>CATEGORY</label>
            <div><input type="text" name="category" class="form-control form-control-sm" placeholder="${product.category.categoryName}" readonly/></div><br/>
            <label>TITLE</label>
            <div><input type="text" name="title" class="form-control form-control-sm" id="title" placeholder="${product.title}" readonly /></div><br/>
            <form action="/adverts" method="post" enctype="multipart/form-data" onsubmit="return isValidFormCreateNewProd()">
                <label>DESCRIPTION</label>
                <div><input type="text" name="description" class="form-control form-control-sm" placeholder="input description" /></div><br/>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label id="priceLabel2">PRICE</label>
                        <div class="input-group input-group-sm">
                            <div class="input-group-prepend">
                                <span class="btn btn-outline-secondary">$</span>
                            </div>
                            <div><input type="text" name="price" id="inputPrice2" class="form-control form-control-sm" aria-label="Amount (to the nearest dollar)"  onkeyup="isValidPrice('inputPrice')" placeholder="0.00"/></div><br/>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label id="shopLabel2">SHOP</label>
                        <select id="shopInput2" name="shop" class="form-control form-control-sm">
                            <option value="EMPTY">Choose...</option>
                            <#list shops as shop>
                                <option value="${shop.nameShop}">${shop.nameShop}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <input type="hidden" name="productId" value="${product.id}" />
                <button type="submit" class="btn btn-secondary" >Create</button>
            </form>
        </div>
    </div>
</div>