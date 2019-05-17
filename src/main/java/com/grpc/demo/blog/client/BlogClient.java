package com.grpc.demo.blog.client;

import com.proto.blog.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BlogClient {

    public static void main(String[] args) {
        System.out.println("Hello gRPC client");

        BlogClient main = new BlogClient();
        main.run();
    }

    private void run() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        Blog createdBlog = createBlog(channel);
        Blog resultBlog = readBlog(channel, createdBlog);
        Blog updatedBlog = updateBlog(channel, resultBlog);
        Blog deletedBlog = deleteBlog(channel, updatedBlog);
//        Blog checkDeletedBlog = readBlog(channel, deletedBlog);
        listBlog(channel);
    }

    private Blog createBlog(ManagedChannel channel) {

        BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);

        Blog blog = Blog.newBuilder()
                .setAuthorId("steven")
                .setTitle("New Blog")
                .setContent("Hello world this is my new blog")
                .build();

        CreateBlogResponse createBlogResponse = blogClient.createBlog(
                CreateBlogRequest.newBuilder()
                        .setBlog(blog)
                        .build()
        );

        System.out.println("Received Create Blog response");
        System.out.println(createBlogResponse.toString());

        return createBlogResponse.getBlog();
    }

    private Blog readBlog(ManagedChannel channel, Blog blog) {

        BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);

        String blogId = blog.getId();

        System.out.println("Reading the blog ...");
        ReadBlogResponse readBlogResponse = blogClient.readBlog(ReadBlogRequest.newBuilder()
                .setBlogId(blogId)
                .build());

        System.out.println("Received Read blog response");
        System.out.println(readBlogResponse.toString());

//        System.out.println("Reading blog with non existing id ...");
//        ReadBlogResponse readBlogResponseNotFound = blogClient.readBlog(ReadBlogRequest.newBuilder()
//                .setBlogId("5cdde90353a9b50797086981")
//                .build());

        return readBlogResponse.getBlog();
    }

    private Blog updateBlog(ManagedChannel channel, Blog blog) {

        BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);

        Blog newBlog = Blog.newBuilder()
                .setId(blog.getId())
                .setAuthorId("Changed Author")
                .setTitle("Updated Blog")
                .setContent("hello I have updated my blog")
                .build();

        System.out.println("Updating Blog ...");
        UpdateBlogResponse updateBlogResponse = blogClient.updateBlog(UpdateBlogRequest.newBuilder().setBlog(newBlog).build());

        System.out.println("Updated Blog");
        System.out.println(updateBlogResponse.toString());

        return updateBlogResponse.getBlog();
    }

    private Blog deleteBlog(ManagedChannel channel, Blog blog) {

        BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);

        System.out.println("Deleting Blog ...");
        DeleteBlogResponse deleteBlogResponse = blogClient.deleteBlog(
                DeleteBlogRequest.newBuilder().setBlogId(blog.getId()).build()
        );
        System.out.println("Deleted Blog");
        System.out.println(deleteBlogResponse.getBlogId().toString());

        return blog;
    }

    private void listBlog(ManagedChannel channel) {

        BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);

        System.out.println("List all blogs ...");
        blogClient.listBlog(ListBlogRequest.newBuilder().build()).forEachRemaining(
                listBlogResponse -> System.out.println(listBlogResponse.getBlog().toString())
        );
    }
}
