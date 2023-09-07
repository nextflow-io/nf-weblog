#!/usr/bin/env python3

from http.server import BaseHTTPRequestHandler, HTTPServer

class EchoHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        print(f'POST {self.path}\n')

        self.send_response(200)
        self.end_headers()

    def do_POST(self):
        content_length = int(self.headers['Content-Length'])
        payload = self.rfile.read(content_length).decode('utf-8')
        print(f'POST {self.path}\n{payload}\n')

        self.send_response(200)
        self.end_headers()

if __name__ == '__main__':
    server = '0.0.0.0'
    port = 8080
    with HTTPServer((server, port), EchoHandler) as httpd:
        print(f'Serving HTTP on {server} port {port} (http://{server}:{port}/) ...')
        try:
            httpd.serve_forever()
        except KeyboardInterrupt:
            print('Keyboard interrupt received, exiting.')
            pass
        httpd.server_close()
