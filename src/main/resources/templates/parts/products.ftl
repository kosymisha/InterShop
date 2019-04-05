<div id="prods">
    Products:<br/>
    <#if products??>
        <#list products as product>
            <object name="objectsProd">
            <img width="100" height="100" src="${product.photoURL}" /><br/>
            <div>${product.title}</div>
            <div>${product.category.categoryName}</div>
            <div><button ">Add</button></div>
            <br/>
            </object>
        </#list>
    <#else>
        No products.
    </#if>
</div>