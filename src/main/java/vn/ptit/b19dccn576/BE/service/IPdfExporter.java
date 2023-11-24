package vn.ptit.b19dccn576.BE.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IPdfExporter {
    void export(HttpServletResponse response) throws IOException;
}
