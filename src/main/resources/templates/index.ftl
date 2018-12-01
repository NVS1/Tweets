<#import "parts/common.ftl" as c>
<@c.page>
<#--<div class="form-row">-->
    <#--<div class="form-group col-md-6">-->
        <#--<form method="get" action="/" class="form-inline">-->
            <#--<input type="text" name="filter" placeholder="Search by Tag" class="form-control">-->
            <#--<button type="submit" class="btn btn-primary ml-2">Search</button>-->
        <#--</form>-->
    <#--</div>-->
<#--</div>-->
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Add new tweet
</a>
<div class="collapse<#if message??>.show<</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" value="<#if message??>${message.text}</#if>" name="text" placeholder="Enter your message" class="form-control ${(textError??)?string('is-invalid', '')}">
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text"  value="<#if message??>${message.tag}</#if>" name="tag" placeholder="Tag" class="form-control ${(tagError??)?string('is-invalid', '')}">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Add tweet</button>
            </div>
        </form>
    </div>
</div>
<div class="card-columns">
    <#list messages as m>
        <div class="card bg-light my-3">
            <#if m.image??>
                <img class="card-img-top" src="/image/${m.imageId}">
            </#if>
            <div class="m-2">
                <div><a href="/?filter=${m.tag}">#${m.tag}</a></div>
                <div><i>${m.date?string('dd.MM.yyyy HH:mm')}</i></div>
                <span>${m.text}</span>
            </div>
            <div class="card-footer text-muted">
                ${m.authorName}
            </div>
        </div>
    <#else >
        <h3>No tweets</h3>
    </#list>
</div>
</@c.page>