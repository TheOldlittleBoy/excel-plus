/**
 * Copyright (c) 2018, biezhi (biezhi.me@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.biezhi.excel.plus.writer;

import io.github.biezhi.excel.plus.exception.WriterException;
import io.github.biezhi.excel.plus.util.StringUtil;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static io.github.biezhi.excel.plus.Constant.XLSX_CONTENT_TYPE;
import static io.github.biezhi.excel.plus.Constant.XLS_CONTENT_TYPE;

/**
 * Used to wrap the HttpServletResponse and download file name
 *
 * @author biezhi
 * @date 2018/12/12
 */
@UtilityClass
public class ResponseWrapper {

    public static OutputStream createXLSX(HttpServletResponse servletResponse, String fileName) throws WriterException {
        servletResponse.setContentType(XLSX_CONTENT_TYPE);
        return create(servletResponse, fileName);
    }

    public static OutputStream createXLS(HttpServletResponse servletResponse, String fileName) throws WriterException {
        servletResponse.setContentType(XLS_CONTENT_TYPE);
        return create(servletResponse, fileName);
    }

    public static OutputStream create(HttpServletResponse servletResponse, String fileName) throws WriterException {
        try {
            if (null == servletResponse) {
                throw new WriterException("response instance not null");
            }
            if (StringUtil.isEmpty(fileName)) {
                throw new WriterException("response file name not empty");
            }
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            servletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            return servletResponse.getOutputStream();
        } catch (Exception e) {
            throw new WriterException(e);
        }
    }

}