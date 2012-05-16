package happytrails

import org.springframework.dao.DataIntegrityViolationException

class RegionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [regionInstanceList: Region.list(params), regionInstanceTotal: Region.count()]
    }

    def create() {
        [regionInstance: new Region(params)]
    }

    def save() {
        def regionInstance = new Region(params)
        if (!regionInstance.save(flush: true)) {
            render(view: "create", model: [regionInstance: regionInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.id])
        redirect(action: "show", id: regionInstance.id)
    }

    def show() {
        def regionInstance = Region.get(params.id)
        if (!regionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "list")
            return
        }

        [regionInstance: regionInstance]
    }

    def edit() {
        def regionInstance = Region.get(params.id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "list")
            return
        }

        [regionInstance: regionInstance]
    }

    def update() {
        def regionInstance = Region.get(params.id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (regionInstance.version > version) {
                regionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'region.label', default: 'Region')] as Object[],
                          "Another user has updated this Region while you were editing")
                render(view: "edit", model: [regionInstance: regionInstance])
                return
            }
        }

        regionInstance.properties = params

        if (!regionInstance.save(flush: true)) {
            render(view: "edit", model: [regionInstance: regionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.id])
        redirect(action: "show", id: regionInstance.id)
    }

    def delete() {
        def regionInstance = Region.get(params.id)
        if (!regionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "list")
            return
        }

        try {
            regionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
