<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    <i class="far fa-plus-square"></i>
</a>
<div class="collapse<#if message??>.show<</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" action="/" enctype="multipart/form-data">
            <input type="hidden" name="url" value="${springMacroRequestContext.getRequestUri()}">
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
                        ${tagError}
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