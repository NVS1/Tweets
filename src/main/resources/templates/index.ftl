<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
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
<div class="card-columns">
    <#list messages as m>
        <div class="card bg-light my-3">
            <div class="card-header container">
                <div class="row">
                    <a class="col align-self-center" href="/?filter=${m.tag}">#${m.tag}</a>
                    <i class="col" >${m.date?string('dd.MM.yyyy HH:mm')}</i>
                </div>
            </div>
            <#if m.image??>
                <img class="card-img-top" src="/image/${m.imageId}">
            </#if>
            <div class="card-body">
                <p class="card-text">${m.text}</p>
            </div>
            <div class="card-footer text-muted">
                <div class="row">
                <a href="#" class="col-sm-6 mr-auto">${m.authorName}</a>
                <a class="col-sm-2 mr-auto" href="#"><i class="far fa-heart"></i></a>
                    <#if m.author.id==userId>
                        <a href="#" class="col-sm-2" data-toggle="modal" data-target="#editModal${m.id}"><i class="far fa-edit"></i></a>
                        <a href="/tweet/delete/${m.id}" class="col-sm-2"><i class="far fa-trash-alt"></i></a>
                    </#if>
                </div>
                <div class="modal fade" id="editModal${m.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Edit tweet</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form method="post" enctype="multipart/form-data" action="/tweet/edit/#{m.id}">
                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Tag:</label>
                                        <input type="text" class="form-control" id="recipient-name" name="tag" value="${m.tag?if_exists}">
                                    </div>
                                    <div class="form-group">
                                        <label for="message-text" class="col-form-label">Message:</label>
                                        <textarea rows="7" cols="40" class="form-control" id="message-text" name="text">${m.text}</textarea>
                                    </div>
                                    <div class="form-group">
                                        <div class="custom-control custom-checkbox">
                                            <input class="custom-control-input" id="delete${m.id}" type="checkbox" name="delete">
                                            <label class="custom-control-label" for="delete${m.id}">Delete image</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="custom-file">
                                            <input type="file" name="file" id="customFile${m.id}">
                                            <label class="custom-file-label" for="customFile${m.id}">Choose file</label>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-primary">Save</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <#else >
        <h3>No tweets</h3>
    </#list>
</div>
</@c.page>

