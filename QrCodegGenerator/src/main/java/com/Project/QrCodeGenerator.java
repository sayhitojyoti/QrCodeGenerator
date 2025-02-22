package com.Project;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

@WebServlet("/QRCodeGeneratorServlet")
public class QrCodeGenerator extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String qrData = request.getParameter("data");

		try {
			int width = 300;
			int height = 300;
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, width, height);

			response.setContentType("image/png");
			OutputStream outputStream = response.getOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
