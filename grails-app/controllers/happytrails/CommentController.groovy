package happytrails

import org.springframework.dao.DataIntegrityViolationException

class CommentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [commentInstanceList: Comment.list(params), commentInstanceTotal: Comment.count()]
    }

    def create() {
        [commentInstance: new Comment(params)]
    }

    def save() {
        def commentInstance = new Comment(params)
        if (!commentInstance.save(flush: true)) {
            render(view: "create", model: [commentInstance: commentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'comment.label', default: 'Comment'), commentInstance.id])
        redirect(action: "show", id: commentInstance.id)
    }

    def show() {
        def commentInstance = Comment.get(params.id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'Comment'), params.id])
            redirect(action: "list")
            return
        }

        [commentInstance: commentInstance]
    }

    def edit() {
        def commentInstance = Comment.get(params.id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'Comment'), params.id])
            redirect(action: "list")
            return
        }

        [commentInstance: commentInstance]
    }

    def update() {
        def commentInstance = Comment.get(params.id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'Comment'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (commentInstance.version > version) {
                commentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'comment.label', default: 'Comment')] as Object[],
                        "Another user has updated this Comment while you were editing")
                render(view: "edit", model: [commentInstance: commentInstance])
                return
            }
        }

        commentInstance.properties = params

        if (!commentInstance.save(flush: true)) {
            render(view: "edit", model: [commentInstance: commentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'comment.label', default: 'Comment'), commentInstance.id])
        redirect(action: "show", id: commentInstance.id)
    }

    def delete() {
        def commentInstance = Comment.get(params.id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'Comment'), params.id])
            redirect(action: "list")
            return
        }

        try {
            commentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'comment.label', default: 'Comment'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'comment.label', default: 'Comment'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
