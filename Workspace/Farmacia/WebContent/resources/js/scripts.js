function verificar(xhr, status, args, dlg, tb) {
        if(args.validationFailed || !args.loggedIn) {
            PF(dlg).jq.effect("shake", {times:5}, 100);
        }
        else {
            PF(dlg).hide();
            PF(tb).clearFilters();
        }
    }