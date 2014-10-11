package org.byvin

class ExcelUploadController {

    def index() { }
	
	def upload() {
		def f = request.getFile('myFile')
		
		GroovyExcelParser obj = new GroovyExcelParser()
		def (headers, rows) = obj.parse(f.getInputStream())
		
		println 'Headers'
		println '------------------'
		headers.each { header ->
		  println header
		}
		println "\n"
		println 'Rows'
		println '------------------'
		rows.each { row ->
		  println obj.toXml(headers, row)
		}
	 
		
		if (f.empty) {
			flash.message = 'file cannot be empty'
			render(view: 'uploadForm')
			return
		}
		
		response.sendError(200, 'Done')
	}
}
