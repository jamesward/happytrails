function getSrc(branch, path, callback, startLineNum, endLineNum) {
    const url = "https://raw.githubusercontent.com/jamesward/happytrails/" + branch + "/" + path;

    $.ajax({
        type: "GET",
        url: url,
        success: function (data) {
            const lines = data.split('\n').slice(startLineNum - 1, endLineNum).join('\n');
            callback(lines);
        }
    });
}

function linkToFile(selector, branch, startLineNum, endLineNum) {
    var base = "https://github.com/jamesward/happytrails/blob/" + branch;
    var filepath = $(selector + "-filename").text();
    var link = base + "/" + filepath;
    if (startLineNum !== undefined) {
        link += "#L" + startLineNum;

        if (endLineNum !== undefined) {
            link += "-L" + endLineNum;
        }
        else {
            link += "-L10000";
        }
    }

    $(selector + "-filename").html($("<a/>").attr("href", link).addClass("roll").append(filepath));
}
