<#import "pager.ftl" as p>
    <@p.pager url page />
    <div class="card-columns">
        <#list page.content as advert>
            <div class="card my-3">
                <div class="row">
                    <div class="col">
                        <img height="170" src="${advert.photoURL}" />
                    </div>
                    <div class="col">
                        <a href="/adverts/${advert.id}">${advert.title}</a> <br/>
                        <b>${advert.intPartPrice}.${advert.fractPartPrice}</b> <sup>${advert.currency}</sup>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    <@p.pager url page />
