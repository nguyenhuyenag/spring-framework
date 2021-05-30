<style>
    .hd-btn {
        width: 150px;
    }
</style>

<div class="container mt-4">
    <div class="search">
        <form class="form-inline mt-2 mt-md-0 float-right" id="search-form">
            <input type="text" id="word" required class="form-control mr-sm-2" placeholder="Search">
            <button id="bth-search" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
    <br /> <br />
    <table class="table table-bordered text-center">
        <thead class="thead-light">
            <tr>
                <th style="width: 20%">No</th>
                <th style="width: 20%">Word</th>
                <th style="width: 20%">Pronounce</th>
                <th style="width: 20%">Translate</th>
                <th style="width: 20%">Edit</th>
            </tr>
        </thead>
        <tbody>
            <tr id="has-result">
                <td><span id="tb-no"></span></td>
                <td><span id="tb-word"></span></td>
                <td><span id="tb-pronounce"></span></td>
                <td><span id="tb-translate"></span></td>
                <td>
                    <button class="btn btn-success">
                        Edit <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                    </button>
                </td>
            </tr>
            <tr id="no-result">
                <td colspan="4"><span class="text-danger">No result</span></td>
            </tr>
        </tbody>
    </table>
    <div class="col text-center">
        <button class="btn btn-primary hd-btn m-1"><span aria-hidden="true">&larr;</span>&nbsp;Previous</button>
        <button class="btn btn-primary hd-btn m-1" id="btn-random" onclick="random();">
            Random&nbsp;
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

    function setData(data) {
        if (StringUtils.isNotEmpty(data)) {
            $('#tb-no').text(data.id);
            $('#tb-word').text(data.word);
            $('#tb-pronounce').text(data.pronounce);
            $('#tb-translate').text(data.translate);
        }
    }

    function search() {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "search?word=" + $("#word").val(),
            success: function (data) {
                if (StringUtils.isEmpty(data)) {
                    $('#no-result').show();
                    $('#has-result').hide();
                } else {
                    $('#no-result').hide();
                    $('#has-result').show();
                    setData(data);                 
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
        $('#no-result').hide();
        $('#has-result').show();
        $('#btn-random').prop('disabled', true);
        setTimeout(function () {
            $.ajax({
                type: "GET",
                url: "random-vocab",
                dataType: 'json',
                contentType: "application/json",
                success: function (data) {
                    setData(data);
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
