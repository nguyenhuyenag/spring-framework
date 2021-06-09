<style>
    .hd-btn {
        width: 150px;
    }
</style>

<div class="container">
    <!-- check box -->
    <div class="p-4 d-flex justify-content-center">
        <div>
            <div tabindex="2" class="form-check">
                <input class="form-check-input" type="checkbox" id="hidden-word">
                <label class="form-check-label" for="hidden-word">Hidden word</label>
            </div>
            <div tabindex="1" class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="hidden-translate">
                <label class="form-check-label" for="hidden-translate">Hidden translate</label>
            </div>
        </div>
    </div>
    <!-- search -->
    <div class="search pb-4 mb-4">
        <form class="form-inline mt-2 mt-md-0 float-right" id="search-form" autocomplete="off">
            <input type="text" id="word" required class="form-control mr-sm-2" placeholder="Search">
            <button id="bth-search" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
    <!-- table-->
    <table class="table table-bordered text-center">
        <thead class="thead-light">
            <tr>
                <th style="width: 10%">No</th>
                <th style="width: 30%">Word</th>
                <th style="width: 30%">Pronounce</th>
                <th style="width: 30%">Translate</th>
                <!-- <th style="width: 20%">Edit</th> -->
            </tr>
        </thead>
        <tbody>
            <tr id="has-result">
                <td><span id="tb-no"></span></td>
                <td><span id="tb-word"></span></td>
                <td><span id="tb-pronounce"></span></td>
                <td><span id="tb-translate"></span></td>
                <!-- <td>
                    <button class="btn btn-success">
                        Edit <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                    </button>
                </td> -->
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
        checkbox('hidden-word');
        checkbox('hidden-translate');

        if ($('#hidden-word').is(":checked")) {
            $('#tb-word').css('color', 'white');
        }
        if ($('#hidden-translate').is(":checked")) {
            $('#tb-translate').css('color', 'white');
        }
    });

    function checkbox(checkboxId) {
        let v = localStorage.getItem(checkboxId);
        if (v == 'true') {
            $('#' + checkboxId).prop('checked', true);
        }
        $('#' + checkboxId).change(function () {
            localStorage.setItem(checkboxId, this.checked);
            if (this.checked) {
                if (checkboxId.endsWith('word')) {
                    $('#tb-word').css('color', 'white');
                }
                if (checkboxId.endsWith('translate')) {
                    $('#tb-translate').css('color', 'white');
                }
            } else {
                if (checkboxId.endsWith('word')) {
                    $('#tb-word').css('color', 'black');
                }
                if (checkboxId.endsWith('translate')) {
                    $('#tb-translate').css('color', 'black');
                }
            }
        });
    }

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
                    // localStorage.setItem('vocab', data);
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
                url: "/random-vocab",
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
        // console.log("key: ", e.key, ", code: ", e.keyCode);
        switch (e.keyCode) {
            //case 32:
                // console.log("key: ", e.key, ", code: ", e.keyCode);
                // checkbox('hidden-translate');
                //break;
            case 37: // ArrowLeft
                break;
            case 39: // ArrowRight
                // console.log(document.activeElement);
                if (document.activeElement.tagName !== 'INPUT') {
                    random();
                }
                break;
            default:
            // console.log(e.key);
        }
    }
</script>