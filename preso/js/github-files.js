function getGithubFile(user, repo, sha, callback, startLineNum, endLineNum) {
    startLineNum = (typeof startLineNum == "undefined") ? 1 : startLineNum
    endLineNum = (typeof endLineNum == "undefined") ? 0 : endLineNum

    var url = "https://api.github.com/repos/" + user + "/" + repo + "/git/blobs/" + sha;

    $.ajax({
        type:"GET",
        url:url,
        dataType:"jsonp",
        success:function (data) {
            if (data.data.message == 'Not Found') {
                callback("Source file not found.")
            } else {
                if (typeof data.data.content != "undefined") {
                    if (data.data.encoding == "base64") {
                        var base64EncodedContent = data.data.content
                        base64EncodedContent = base64EncodedContent.replace(/\n/g, "")

                        var content = window.atob(base64EncodedContent)

                        var contentArray = content.split("\n")

                        if (endLineNum == 0) {
                            endLineNum = contentArray.length
                        }

                        callback(contentArray.slice(startLineNum - 1, endLineNum).join("\n"), data.data.url)

                    }
                }
            }
        }
    });
}

function getSource(sha, callback, startLineNum, endLinNum) {
    return getGithubFile("jamesward", "happytrails", sha, callback, startLineNum, endLinNum);
}

function linkToFile(selector, branch, lineNo) {
    var base = "https://github.com/jamesward/happytrails/blob/" + branch;
    var filepath = $(selector + "-filename").text();
    var link = base + "/" + filepath;
    if (lineNo) {
        link += "#L" + lineNo;
    }

    $(selector + "-filename").html($("<a/>").attr("href", link).addClass("roll").append(filepath));
}