window.document.onkeydown = disableRefresh;

function disableRefresh(evt) {
    evt = (evt) ? evt : window.event
    if (evt.keyCode) {
        //-------A
        if (evt.keyCode == 65) {
            var ieKey = evt.keyCode;
            //alert(String.fromCharCode(ieKey));
            tk.showSuccessMsg("success");
        }
        //--------S
        if (evt.keyCode == 83) {
            var ieKey = evt.keyCode;
            // alert(String.fromCharCode(ieKey));
            tk.showErrorMsg("error");
        }
        //--------D
        if (evt.keyCode == 68) {
            var ieKey = evt.keyCode;
            //alert(String.fromCharCode(ieKey));
            tk.showWarningMsg("warning");
        }
        //--------F
        if (evt.keyCode == 70) {
            var ieKey = evt.keyCode;
            //alert(String.fromCharCode(ieKey));
            tk.showInfoMsg("info");
        }
        //--------G
        if (evt.keyCode == 71) {
            var ieKey = evt.keyCode;
            //alert(String.fromCharCode(ieKey));
            tk.mask("正在处理中");
        }
        //--------H
        if (evt.keyCode == 72){
            var ieKey = evt.keyCode;
            //alert(String.fromCharCode(ieKey));
             tk.unmask();
        }
    }
}
