<#include "../parts/security.ftl" />
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
                                <#else >
                                    No products.
                                </#list>
                            </div>
                        <#else>
                            No products.
                        </#if>
</div>