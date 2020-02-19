<#import "parts/common.ftl" as c>


<@c.page>
    <div class="form-row">
        <form method="get" action="/main" class="form-inline" >
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by INN">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>

    <a class="btn btn-primary mt-3 mb-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new user
    </a>

    <h5 class="mt-2 mb-2">Enter Persons date</h5>

    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
        <form method="post"  enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="personId" class="form-control" placeholder="Enter the INN">
            </div>
            <div class="form-group">
            <input type="text" name="firstName" class="form-control" placeholder="Enter the firstName">
            </div>
            <div class="form-group">
                <input type="text" name="lastName" class="form-control" placeholder="Enter the lastName">
            </div>
            <div class="form-group">
                <input type="text" name="dateOfBirth" class="form-control" placeholder="Enter the dateOfBirth">

            </div>
            <div class="form-group">
                <input type="text" name="familyStatus" class="form-control" placeholder="Enter the familyStatus">
            </div>
            <div class="form-group">
                <input type="text" name="education"class="form-control"  placeholder="Enter the education">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </form>
        </div>
    </div>

    <div class="mt-3 mb-3">Users List</div>

<div class="card-columns">
    <#list persons as person >
        <div class="card my-3">
            <div>
                <#if person.filename??>
                    <img src="/img/${person.filename}" class="card-img-top">
                </#if>
            </div>
            <div class="m-2">
                <b>${person.id}</b>&nbsp;
                <span>${person.personId}</span>&nbsp;
                <span>${person.firstName}</span>&nbsp;
                <span>${person.lastName}</span>&nbsp;
                <span>${person.dateOfBirth}</span>&nbsp;
                <span>${person.familyStatus}</span>&nbsp;
                <span>${person.education}</span>&nbsp;
            </div>

            <div class="card-footer text-muted">
                <strong>${person.authorName}</strong>
            </div>
        </div>
    <#else>
        No persons
    </#list>
</div>
</@c.page>