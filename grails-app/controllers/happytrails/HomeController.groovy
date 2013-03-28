package happytrails

import org.grails.comments.Comment
import grails.plugin.cache.Cacheable

class HomeController {

    @Cacheable("home")
    def index() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [regions: Region.list(params), total: Region.count(),
         comments: Comment.list(max: 10, sort: 'dateCreated', order: 'desc')]
    }
}
