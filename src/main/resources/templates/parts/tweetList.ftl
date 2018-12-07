<#include "security.ftl">
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
                    <a href="/tweets/${m.author.id}" class="col-sm-6 mr-auto">
                        ${m.authorName}
                    </a>
                    <a class="col-sm-2 mr-auto" href="#">
                    <#if true>
                        <i class="fas fa-thumbs-up"></i>
                    <#else >
                        <i class="far fa-thumbs-up"></i>
                    </#if>
                    </a>
                    <#if m.author.id==userId>
                        <a href="#" class="col-sm-2" data-toggle="modal" data-target="#editModal${m.id}"><i class="far fa-edit"></i></a>
                        <a href="/tweet/delete/${m.id}?url=${springMacroRequestContext.getRequestUri()}" class="col-sm-2"><i class="far fa-trash-alt"></i></a>
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
                                    <input type="hidden" name="currentUrl" value="${springMacroRequestContext.getRequestUri()}">
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