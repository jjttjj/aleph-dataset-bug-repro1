# aleph-dataset-bug-repro1

```
git clone https://github.com/jjttjj/aleph-dataset-bug-repro1/
cd aleph-dataset-bug-repro1
clj -X demo.core/start
```

Will result in 

```
java.lang.IllegalArgumentException: contains? not supported on type: manifold.deferred.Deferred
        at clojure.lang.RT.contains(RT.java:853)
        at clojure.core$contains_QMARK_.invokeStatic(core.clj:1494)
        at clojure.core$contains_QMARK_.invoke(core.clj:1486)
        at ring.middleware.flash$flash_response.invokeStatic(flash.clj:21)
        at ring.middleware.flash$flash_response.invoke(flash.clj:14)
        at ring.middleware.flash$wrap_flash$fn__22696.invoke(flash.clj:39)
        at ring.middleware.session$wrap_session$fn__23127.invoke(session.clj:108)
        at ring.middleware.keyword_params$wrap_keyword_params$fn__23173.invoke(keyword_params.clj:53)
        at ring.middleware.nested_params$wrap_nested_params$fn__23231.invoke(nested_params.clj:89)
        at ring.middleware.multipart_params$wrap_multipart_params$fn__23529.invoke(multipart_params.clj:171)
        at ring.middleware.params$wrap_params$fn__23553.invoke(params.clj:67)
        at ring.middleware.cookies$wrap_cookies$fn__23006.invoke(cookies.clj:214)
        at ring.middleware.absolute_redirects$wrap_absolute_redirects$fn__23741.invoke(absolute_redirects.clj:47)
        at ring.middleware.content_type$wrap_content_type$fn__23689.invoke(content_type.clj:34)
        at ring.middleware.default_charset$wrap_default_charset$fn__23713.invoke(default_charset.clj:31)
        at ring.middleware.not_modified$wrap_not_modified$fn__23655.invoke(not_modified.clj:61)
        at ring.middleware.x_headers$wrap_x_header$fn__22659.invoke(x_headers.clj:22)
        at ring.middleware.x_headers$wrap_x_header$fn__22659.invoke(x_headers.clj:22)
        at ring.middleware.x_headers$wrap_x_header$fn__22659.invoke(x_headers.clj:22)
        at clojure.lang.AFn.applyToHelper(AFn.java:154)
        at clojure.lang.AFn.applyTo(AFn.java:144)
        at clojure.lang.AFunction$1.doInvoke(AFunction.java:31)
        at clojure.lang.RestFn.invoke(RestFn.java:408)
        at aleph.http.server$handle_request$fn__21296$f__15491__auto____21297.invoke(server.clj:170)
        at clojure.lang.AFn.run(AFn.java:22)
        at io.aleph.dirigiste.Executor$Worker$1.run(Executor.java:62)
        at manifold.executor$thread_factory$reify__15373$f__15374.invoke(executor.clj:47)
        at clojure.lang.AFn.run(AFn.java:22)
        at java.base/java.lang.Thread.run(Thread.java:832)
```

Commenting out the 
`techascent/tech.ml.dataset` dependency in `deps.edn` will remove this error.
