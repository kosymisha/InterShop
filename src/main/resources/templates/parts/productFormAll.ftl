<#include "../parts/security.ftl" />
<div id="productForm">
    <label>TITLE</label>
    <div><input type="text" name="title" class="form-control" id="titleProd" placeholder="input title" onkeyup="inputProductTitle()" /></div><br/>
    <div id="prods">
        <#if products??>
            <div class="card-columns m-2">
                <#list products as product>
                    <object name="objectsProd">
                        <div class="card m-2" >
                            <img src="${product.photoURL}" /><br/>
                            <div>${product.title}</div>
                            <div>${product.category.categoryName}</div>
                            <div><button class="btn btn-secondary" onclick="chooseProduct('${product.id}', '${currentUserId}')">Add</button></div>
                        </div>
                    </object>
                </#list>
            </div>
        <#else>
            No products.
        </#if>
    </div>
</div>