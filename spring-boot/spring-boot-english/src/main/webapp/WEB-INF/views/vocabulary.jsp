<div class="container text-center">
    <h1 class="m-4">Vocabulary</h1>
    <div class="search">
        <form class="form-inline mt-2 mt-md-0 float-right" id="search-form">
            <input class="form-control mr-sm-2" type="text" id="word" placeholder="Search">
            <button id="bth-search" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
    <table class="table table-bordered">
        <thead class="thead-light">
            <tr>
                <th style="width: 20%">No</th>
                <th style="width: 40%">Vocabulary</th>
                <th style="width: 40%">Pronounce</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><span id="no"></span></td>
                <td><span id="word"></span></td>
                <td><span id="pronounce"></span></td>
            </tr>
            <tr id="no-result">
                <td colspan="3"><span class="text-danger">No result</span></td>
            </tr>
        </tbody>
    </table>
    <div class="col">
        <button class="btn btn-primary m-1">Previous</button>
        <button onclick="random();" class="btn btn-primary m-1">Next</button>
    </div>
</div>

<style>
    .btn {
        width: 150px;
    }
</style>

<script type="text/javascript">
    $(function () {
        $('#no-result').hide();
        random();
        $("#search-form").submit(function (event) {
            // stop submit the form, we will post it manually
            event.preventDefault();
            search();
        });
    });
    function random() {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "random-vocab",
            dataType: 'json',
            success: function (data) {
                // $('#no-result').show();
                $('#no').text(data.id);
                $('#word').text(data.word);
                $('#pronounce').text(data.pronounce);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }
    function search() {
        // console.log('aaaaaaaa');
        var search = {}
        search["word"] = $("#word").val();
        $("#btn-search").prop("disabled", true);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "search?word=" + $("#word").val(),
            success: function (data) {
                console.log("CHECK : ", isNull(data));
                console.log("SUCCESS : ", data);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
        $("#btn-search").prop("disabled", false);
    }
</script>