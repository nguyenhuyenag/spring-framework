
<div class="container">
    <div class="bg-warning">
        <form class="form-inline" id="noun-form">
            <input id="noun" class="form-control mr-sm-2" placeholder="Search" required>
            <button type="submit" class="btn btn-outline-success">OK</button>
        </form>
        <div class="result mt-3"></div>
    </div>

    <div class="d-flex">
        <input class="form-control mr-1">
        <button class="btn btn-primary">enter</button>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $("#noun-form").submit(function (event) {
            event.preventDefault();
            send();
        });
    });

    function send() {
        $.ajax({
            type: "POST",
            url: "plural-noun?noun=" + $("#noun").val(),
            success: function (data) {
                console.log(data);
                if (StringUtils.isNotEmpty(data)) {
                    $('.result').addClass('bg-success');
                    $('.result').text(data);
                }
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }

</script>
