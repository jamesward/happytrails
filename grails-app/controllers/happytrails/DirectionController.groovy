package happytrails

import org.springframework.dao.DataIntegrityViolationException

class DirectionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [directionInstanceList: Direction.list(params), directionInstanceTotal: Direction.count()]
    }

    def create() {
        [directionInstance: new Direction(params)]
    }

    def save() {
        def directionInstance = new Direction(params)
        if (!directionInstance.save(flush: true)) {
            render(view: "create", model: [directionInstance: directionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'direction.label', default: 'Direction'), directionInstance.instruction])
        redirect(controller: "route", action: "edit", id: directionInstance.routeId)
    }

    def show() {
        def directionInstance = Direction.get(params.id)
        if (!directionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'direction.label', default: 'Direction'), params.id])
            redirect(action: "list")
            return
        }

        [directionInstance: directionInstance]
    }

    def edit() {
        def directionInstance = Direction.get(params.id)
        if (!directionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'direction.label', default: 'Direction'), params.id])
            redirect(action: "list")
            return
        }

        [directionInstance: directionInstance]
    }

    def update() {
        def directionInstance = Direction.get(params.id)
        if (!directionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'direction.label', default: 'Direction'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (directionInstance.version > version) {
                directionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'direction.label', default: 'Direction')] as Object[],
                        "Another user has updated this Direction while you were editing")
                render(view: "edit", model: [directionInstance: directionInstance])
                return
            }
        }

        directionInstance.properties = params

        if (!directionInstance.save(flush: true)) {
            render(view: "edit", model: [directionInstance: directionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'direction.label', default: 'Direction'), directionInstance.stepNumber])
        redirect(controller: "route", action: "edit", id: directionInstance.routeId)
    }

    def delete() {
        def directionInstance = Direction.get(params.id)
        if (!directionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'direction.label', default: 'Direction'), params.id])
            redirect(action: "list")
            return
        }

        try {
            directionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'direction.label', default: 'Direction'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'direction.label', default: 'Direction'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
