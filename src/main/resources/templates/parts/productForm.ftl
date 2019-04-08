<div id="productForm">
    <button class="btn btn-secondary" onclick="backToAllProducts()" >Back to all products</button>
    <div class="card-columns m-2">
            <object name="objectsProd">
                <div class="card m-2" >
                    <img src="${product.photoURL}" /><br/>
                    <div>${product.title}</div>
                    <div>${product.category.categoryName}</div>
                </div>
            </object>
    </div>
    <label>CATEGORY</label>
    <div><input type="text" name="category" class="form-control form-control-sm" placeholder="${product.category.categoryName}" readonly/></div><br/>
    <label>TITLE</label>
    <div><input type="text" name="title" class="form-control form-control-sm" id="title" placeholder="${product.title}" readonly /></div><br/>
    <form action="/adverts" method="post" enctype="multipart/form-data" onsubmit="return isValidFormCreateNewProd()">
        <label>DESCRIPTION</label>
        <div><input type="text" name="description" class="form-control form-control-sm" placeholder="input description" /></div><br/>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label id="priceLabel">PRICE</label>
                <div class="input-group input-group-sm">
                    <div class="input-group-prepend">
                        <span class="btn btn-outline-secondary">$</span>
                    </div>
                    <div><input type="text" name="price" id="inputPrice" class="form-control form-control-sm" aria-label="Amount (to the nearest dollar)"  onkeyup="isValidPrice('inputPrice')" placeholder="0.00"/></div><br/>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label>SHOP</label>
                <select name="shop" class="form-control form-control-sm">
                    <#list shops as shop>
                        <option value="${shop.nameShop}">${shop.nameShop}</option>
                    </#list>
                </select>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="hidden" name="productId" value="${product.id}" />
        <button type="submit" class="btn btn-secondary" >Create</button>
    </form>
</div>