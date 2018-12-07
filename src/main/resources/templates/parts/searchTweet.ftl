<#macro search action>
    <a class="btn btn-success my-2 my-sm-0" data-toggle="collapse" href="#collapseSearch" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        <i class="fas fa-search"></i>
    </a>
<div class="collapse" id="collapseSearch">
    <div class="form-group mt-3">
        <div class="form-row">
            <div class="form-group col-md-6">
                <form method="get" action="${action}" class="form-inline">
                    <input type="text" name="filter" placeholder="Search by Tag" class="form-control">
                    <button type="submit" class="btn btn-primary ml-2">Search</button>
                </form>
            </div>
        </div>
    </div>
</div>
</#macro>