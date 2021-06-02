<style>
    button {
        width: 100px;
    }
</style>

<div class="container">
    <div class="" align=center>
        <h1>Plural Noun</h1>
        <form id="noun-form" style="width: 300px;">
            <div class="form-group">
                <input id="noun" class="form-control mr-sm-2" placeholder="Search" required>
            </div>
            <button type="submit" class="btn btn-outline-success">OK</button>
        </form>
        <div class="result mt-1 display-4"></div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        handleRequiredMessage('');
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
                    // $('.result').addClass('bg-success');
                    $('.result').text(data);
                }
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }

</script>
