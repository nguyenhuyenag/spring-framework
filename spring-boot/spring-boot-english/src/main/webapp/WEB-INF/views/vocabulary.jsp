<style>
    .btn {
        width: 150px;
    }
</style>

<div class="container text-center">
    <h1 class="m-4">Vocabulary</h1>
    <div class="search">
        <form class="form-inline mt-2 mt-md-0 float-right" id="search-form">
            <input type="text" id="word" required class="form-control mr-sm-2" placeholder="Search">
            <button id="bth-search" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
    <table class="table table-bordered">
        <thead class="thead-light">
            <tr>
                <th style="width: 10%">No</th>
                <th style="width: 30%">Word</th>
                <th style="width: 30%">Pronounce</th>
                <th style="width: 30%">Translate</th>
            </tr>
        </thead>
        <tbody>
            <tr id="has-result">
                <td><span id="no"></span></td>
                <td><span id="_word"></span></td>
                <td><span id="pronounce"></span></td>
                <td><span id="translate"></span></td>
            </tr>
            <tr id="no-result">
                <td colspan="4"><span class="text-danger">No result</span></td>
            </tr>
        </tbody>
    </table>
    <div class="col">
        <button class="btn btn-primary m-1"><span aria-hidden="true">&larr;</span>&nbsp;Previous</button>
        <button id="btn-random" class="btn btn-primary m-1" onclick="random();">
            Next&nbsp;
            <span id="icon-next" aria-hidden="true">&rarr;</span>
            <span id="icon-loading" class="spinner-border spinner-border-sm"></span>
        </button>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#no-result').hide();
        $('#icon-loading').hide();

        $("#search-form").submit(function (event) {
            // stop submit the form, we will post it manually
            event.preventDefault();
            search();
        });

        random();
    });

    function search() {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "search?word=" + $("#word").val(),
            success: function (data) {
                // console.log("DATA : ", data);
                if (StringUtils.isEmpty(data)) {
                    $('#no-result').show();
                    $('#has-result').hide();
                } else {
                    $('#no-result').hide();
                    $('#has-result').show();
                }
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }

    function random() {
        $('#icon-next').hide();
        $('#icon-loading').show();
        $('#btn-random').prop('disabled', true);
        setTimeout(function () {
            $.ajax({
                type: "GET",
                url: "random-vocab",
                dataType: 'json',
                contentType: "application/json",
                success: function (data) {
                    $('#no').text(data.id);
                    $('#_word').text(data.word);
                    $('#pronounce').text(data.pronounce);
                    $('#translate').text(data.translate);
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                }
            });
            $('#icon-next').show();
            $('#icon-loading').hide();
            $('#btn-random').prop('disabled', false);
        }, 150);
    }

    document.onkeydown = function (e) {
        // console.log(e.key);
        // console.log(e.keyCode);
        switch (e.key) {
            case 'ArrowLeft':
                console.log(e.key);
                break;
            case 'ArrowRight':
                random();
                break;
            default:
                console.log(e.key);
        }
    }
</script>
