package happytrails

import org.springframework.dao.DataIntegrityViolationException

class RouteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        println 'index'
        redirect(action: "list", params: params)
    }

    def list() {
        println 'list'
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [routeInstanceList: Route.list(params), routeInstanceTotal: Route.count()]
    }

    def create() {
        [routeInstance: new Route(params)]
    }

    def save() {
        println ('saving')
        def routeInstance = new Route(params)
        if (!routeInstance.save(flush: true)) {
            render(view: "create", model: [routeInstance: routeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'route.label', default: 'Route'), routeInstance.id])
        redirect(action: "show", id: routeInstance.id)
    }

    def find() {
        println("Finding route: " + params.route)
        def route = Route.findBySeoName(params.route)

        render(view: "show", model: [routeInstance: route])
    }

    def show() {
        def routeInstance = Route.get(params.id)
        if (!routeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'route.label', default: 'Route'), params.id])
            redirect(action: "list")
            return
        }

        [routeInstance: routeInstance]
    }

    def edit() {
        def routeInstance = Route.get(params.id)
        if (!routeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'route.label', default: 'Route'), params.id])
            redirect(action: "list")
            return
        }

        [routeInstance: routeInstance]
    }

    def update() {
        def routeInstance = Route.get(params.id)
        if (!routeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'route.label', default: 'Route'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (routeInstance.version > version) {
                routeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'route.label', default: 'Route')] as Object[],
                          "Another user has updated this Route while you were editing")
                render(view: "edit", model: [routeInstance: routeInstance])
                return
            }
        }

        routeInstance.properties = params

        if (!routeInstance.save(flush: true)) {
            render(view: "edit", model: [routeInstance: routeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'route.label', default: 'Route'), routeInstance.id])
        redirect(action: "show", id: routeInstance.id)
    }

    def delete() {
        def routeInstance = Route.get(params.id)
        if (!routeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'route.label', default: 'Route'), params.id])
            redirect(action: "list")
            return
        }

        try {
            routeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'route.label', default: 'Route'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'route.label', default: 'Route'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
