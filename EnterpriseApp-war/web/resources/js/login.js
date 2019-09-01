
function handleLoginRequest(xhr, status, args) {
    if (args.validationFailed || !args.loggedIn) {
        dlg.jq.effect("shake", {times: 5}, 100);
    }
    else {
        dlg.hide();
        $('#loginLink').fadeOut();
    }
}
