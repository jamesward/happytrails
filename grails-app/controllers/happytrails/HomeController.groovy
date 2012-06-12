package happytrails

import org.grails.comments.Comment

class HomeController {

    def index() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [regions: Region.list(params), total: Region.count(), comments: Comment.list(max: 10)]
    }
}
